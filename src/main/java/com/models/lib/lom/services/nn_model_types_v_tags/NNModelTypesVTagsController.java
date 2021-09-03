package com.models.lib.lom.services.nn_model_types_v_tags;

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
public class NNModelTypesVTagsController {

    private final NNModelTypesVTagsDao dao;

    @Autowired
    public NNModelTypesVTagsController(NNModelTypesVTagsDao dao) {
        this.dao = dao;
    }
    
    @PostMapping("/api/nn_model_types_v_tags")
    public List<Object> create(@RequestBody List<NNModelTypesVTags> entities) {
    	return dao.create(entities);
    }
    
    @GetMapping("/api/nn_model_types_v_tags/{id}")
    public NNModelTypesVTags get(@PathVariable(value = "id") Long id,
			   					 @RequestParam(value = "complex", required = false) Boolean complex) {
    	
    	Query query = new Query(complex, new Condition(NNModelTypesVTagsTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return dao.selectOne(query);
    }

    @GetMapping("/api/nn_model_types_v_tags")
    public List<NNModelTypesVTags> list(@RequestParam(value = "ids", required = false) List<String> ids,
    						 			@RequestParam(value = "pageSize", required = false) Integer pageSize,
                             			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
  			  			   				@RequestParam(value = "complex", required = false) Boolean complex) {
    	        
    	Query query = new Query(complex);

        if (ids != null) query.addCondition(new Query.Condition(NNModelTypesVTagsTable.colId, Query.Comparator.in, ids));
                
        return dao.select(query);
    }

    @PutMapping("/api/nn_model_types_v_tags")
    public List<Object> update(@RequestBody List<NNModelTypesVTags> entities) {
    	return dao.update(entities);
    }

    @DeleteMapping("/api/nn_model_types_v_tags")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return dao.delete(filesIds);
    }
}