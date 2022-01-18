package com.lom.services.contributors;

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

import com.lom.components.services.Query;

@RestController
public class ContributorsController {

    private final ContributorsService service;
    
    @Autowired
    public ContributorsController(ContributorsService service) {        
        this.service = service;
    }

    @PostMapping("/api/contributors")
    public List<Object> create(@RequestBody List<Contributors> entities) {
    	return service.create(entities);
    }

    @GetMapping("/api/contributors/{id}")
    public Contributors get(@PathVariable(value = "id") Long id,
            				@RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	    	
    	return service.selectOne(ContributorsTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/contributors")
    public List<Contributors> list(@RequestParam(value = "ids", required = false) List<String> ids,
	                               @RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
        
        return service.select(ContributorsTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/contributors")
    public List<Object> update(@RequestBody List<Contributors> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/contributors")
    public List<Object> delete(@RequestBody List<Object> contributorIds) {
    	return service.delete(contributorIds);
    }
}