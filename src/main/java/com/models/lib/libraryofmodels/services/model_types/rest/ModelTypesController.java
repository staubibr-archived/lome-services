package com.models.lib.libraryofmodels.services.model_types.rest;

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
import com.models.lib.libraryofmodels.services.model_types.ModelTypesManager;
import com.models.lib.libraryofmodels.services.model_types.model.ModelTypes;
import com.models.lib.libraryofmodels.services.model_types.model.ModelTypesQuery;
import com.models.lib.libraryofmodels.utils.RESTResponse;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class ModelTypesController {

    private final ModelTypesManager modelTypesManager;

    @Autowired
    public ModelTypesController(ModelTypesManager modelTypesManager) {
        this.modelTypesManager = modelTypesManager;
    }

    @GetMapping("/api/modeltypes/{id}")
    public ModelTypes get(@PathVariable(value = "id") Long id) {
        log.info("Getting model type object with id {}", id);
        return modelTypesManager.get(id);
    }

    @GetMapping("/api/modeltypes")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	
        log.info("Getting Model Types");
        
        ModelTypesQuery searchQuery = ModelTypesQuery.builder()
										                 .pageSize(pageSize)
										                 .pageNumber(pageNumber)
										                 .build();
        
        Page<ModelTypes> results = modelTypesManager.search(searchQuery);
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }
    
    @PutMapping("/api/modeltypes")
    public RESTResponse update(@RequestBody List<ModelTypes> modeltypes) {
        log.info("Updating modeltypes");
        modelTypesManager.update(modeltypes);
        return RESTResponse.builder().data(modeltypes).build();
    }

    @DeleteMapping("/api/modeltypes")
    public RESTResponse delete(@RequestBody List<String> modeltypesIds) {
        log.info("Deleting modeltypes");
        modelTypesManager.delete(modeltypesIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/modeltypes")
    public RESTResponse create(@RequestBody ModelTypes entity) {
    	log.info("Creating a modeltype");
    	modelTypesManager.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}