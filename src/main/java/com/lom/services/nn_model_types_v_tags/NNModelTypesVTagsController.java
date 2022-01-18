package com.lom.services.nn_model_types_v_tags;

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
public class NNModelTypesVTagsController {

    private final NNModelTypesVTagsService service;

    @Autowired
    public NNModelTypesVTagsController(NNModelTypesVTagsService service) {
        this.service = service;
    }
    
    @PostMapping("/api/nn_model_types_v_tags")
    public List<Object> create(@RequestBody List<NNModelTypesVTags> entities) {
    	return service.create(entities);
    }
    
    @GetMapping("/api/nn_model_types_v_tags/{id}")
    public NNModelTypesVTags get(@PathVariable(value = "id") Long id,
			   					 @RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	
    	return service.selectOne(NNModelTypesVTagsTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/nn_model_types_v_tags")
    public List<NNModelTypesVTags> list(@RequestParam(value = "ids", required = false) List<String> ids,
  			  			   				@RequestParam(value = "complex", defaultValue = "false") Boolean complex) {

        return service.select(NNModelTypesVTagsTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/nn_model_types_v_tags")
    public List<Object> update(@RequestBody List<NNModelTypesVTags> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/nn_model_types_v_tags")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return service.delete(filesIds);
    }
}