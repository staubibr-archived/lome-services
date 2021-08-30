package com.models.lib.libraryofmodels.services.model_types;

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
public class ModelTypesController {

    private final Dao<ModelTypes> dao;

    @Autowired
    public ModelTypesController(Dao<ModelTypes> dao) {
        this.dao = dao;
    }

    @GetMapping("/api/modeltypes/{id}")
    public ModelTypes get(@PathVariable(value = "id") Long id) {
        return dao.get(id.toString());
    }

    @GetMapping("/api/modeltypes")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	        
        ModelTypesQuery query = ModelTypesQuery.builder()
							                   .pageSize(pageSize)
							                   .pageNumber(pageNumber)
							                   .build();
        
        Page<ModelTypes> results = dao.search(query.ToWhere());
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }
    
    @PutMapping("/api/modeltypes")
    public RESTResponse update(@RequestBody List<ModelTypes> modeltypes) {
        dao.update(modeltypes);
        return RESTResponse.builder().data(modeltypes).build();
    }

    @DeleteMapping("/api/modeltypes")
    public RESTResponse delete(@RequestBody List<String> modeltypesIds) {
    	dao.delete(modeltypesIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/modeltypes")
    public RESTResponse create(@RequestBody ModelTypes entity) {
    	dao.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}