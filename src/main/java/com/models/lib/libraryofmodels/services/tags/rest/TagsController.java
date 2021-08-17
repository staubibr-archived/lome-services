package com.models.lib.libraryofmodels.services.tags.rest;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.tags.TagsManager;
import com.models.lib.libraryofmodels.services.tags.model.Tags;
import com.models.lib.libraryofmodels.services.tags.model.TagsQuery;
import com.models.lib.libraryofmodels.utils.RESTResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TagsController {

    private final TagsManager tagsManager;

    @Autowired
    public TagsController(TagsManager tagsManager) {
        this.tagsManager = tagsManager;
    }

    @GetMapping("/api/tags/{id}")
    public Tags get(@PathVariable(value = "id") Long id) {
        log.info("Getting tags object with id {}", id);
        return tagsManager.get(id);
    }

    @GetMapping("/api/tags")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	
        log.info("Getting tags objects");
        
        TagsQuery searchQuery = TagsQuery.builder()
										                 .pageSize(pageSize)
										                 .pageNumber(pageNumber)
										                 .build();
        
        Page<Tags> results = tagsManager.search(searchQuery);
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/tags")
    public RESTResponse update(@RequestBody List<Tags> tags) {
        log.info("Updating tags");
        tagsManager.update(tags);
        return RESTResponse.builder().data(tags).build();
    }

    @DeleteMapping("/api/tags")
    public RESTResponse delete(@RequestBody List<String> tagsIds) {
        log.info("Deleting tags");
        tagsManager.delete(tagsIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/tags")
    public RESTResponse create(@RequestBody Tags entity) {
        log.info("Creating a tag");
        tagsManager.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}