package com.models.lib.lom.services.model_types;

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

import com.models.lib.lom.components.Query;
import com.models.lib.lom.components.Query.Condition;

@RestController
public class ModelTypesController {

    private final ModelTypesService service;

    @Autowired
    public ModelTypesController(ModelTypesService service) {
        this.service = service;
    }
    
    @PostMapping("/api/modeltypes")
    public List<Object> create(@RequestBody List<ModelTypes> entities) {
    	return service.create(entities);
    }

    @GetMapping("/api/modeltypes/{id}")
    public ModelTypes get(@PathVariable(value = "id") Long id,
						  @RequestParam(value = "complex", required = false) Boolean complex) {
    	
    	Query query = new Query(complex, new Condition(ModelTypesTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return service.selectOne(query);
    }

    @GetMapping("/api/modeltypes")
    public List<ModelTypes> list(@RequestParam(value = "ids", required = false) List<String> ids,
							 	 @RequestParam(value = "pageSize", required = false) Integer pageSize,
							 	 @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
	            				 @RequestParam(value = "complex", required = false) Boolean complex) {
    	
        Query query = new Query(complex);

        if (ids != null) query.addCondition(new Query.Condition(ModelTypesTable.colId, Query.Comparator.in, ids));
                
        return service.select(query);
    }
    
    @PutMapping("/api/modeltypes")
    public List<Object> update(@RequestBody List<ModelTypes> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/modeltypes")
    public List<Object> delete(@RequestBody List<Object> modeltypesIds) {
    	return service.delete(modeltypesIds);
    }
}