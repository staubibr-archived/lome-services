package com.lome.services.tags;

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

import com.lome.components.Query;

@RestController
public class TagsController {

    private final TagsService service;

    @Autowired
    public TagsController(TagsService service) {
        this.service = service;
    }
    
    @PostMapping("/api/tags")
    public List<Object> create(@RequestBody List<Tags> entities) {
    	return service.create(entities);
    }

    @GetMapping("/api/tags/{id}")
    public Tags get(@PathVariable(value = "id") Long id,
				 	@RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	
    	return service.selectOne(TagsTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/tags")
    public List<Tags> list(@RequestParam(value = "ids", required = false) List<String> ids,
		   				   @RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	        
        return service.select(TagsTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/tags")
    public List<Object> update(@RequestBody List<Tags> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/tags")
    public List<Object> delete(@RequestBody List<Object> tagsIds) {
    	return service.delete(tagsIds);
    }
}