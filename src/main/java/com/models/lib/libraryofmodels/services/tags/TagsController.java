package com.models.lib.libraryofmodels.services.tags;

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

import com.models.lib.libraryofmodels.services.db.Dao;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.utils.RESTResponse;

@RestController
public class TagsController {

    private final Dao<Tags> dao;

    @Autowired
    public TagsController(Dao<Tags> dao) {
        this.dao = dao;
    }

    @GetMapping("/api/tags/{id}")
    public Tags get(@PathVariable(value = "id") Long id) {
        return dao.get(id.toString());
    }

    @GetMapping("/api/tags")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	        
        TagsQuery query = TagsQuery.builder()
								   .pageSize(pageSize)
								   .pageNumber(pageNumber)
								   .build();
        
        Page<Tags> results = dao.search(query.ToWhere());
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/tags")
    public RESTResponse update(@RequestBody List<Tags> tags) {
        dao.update(tags);
        return RESTResponse.builder().data(tags).build();
    }

    @DeleteMapping("/api/tags")
    public RESTResponse delete(@RequestBody List<String> tagsIds) {
        dao.delete(tagsIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/tags")
    public RESTResponse create(@RequestBody Tags entity) {
        dao.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}