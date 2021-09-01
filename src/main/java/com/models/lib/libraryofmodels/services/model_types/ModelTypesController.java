package com.models.lib.libraryofmodels.services.model_types;

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
import com.models.lib.libraryofmodels.services.db.Query;

@RestController
public class ModelTypesController {

    private final Dao<ModelTypes> dao;

    @Autowired
    public ModelTypesController(Dao<ModelTypes> dao) {
        this.dao = dao;
    }
    
    @PostMapping("/api/modeltypes")
    public List<Object> create(@RequestBody List<ModelTypes> entities) {
    	return dao.create(entities);
    }

    @GetMapping("/api/modeltypes/{id}")
    public ModelTypes get(@PathVariable(value = "id") Long id) {
    	Query query = new Query();
    	
    	query.addCondition(new Query.Condition(ModelTypesTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return dao.selectOne(query);
    }

    @GetMapping("/api/modeltypes")
    public List<ModelTypes> list(@RequestParam(value = "ids", required = false) String ids,
							 @RequestParam(value = "pageSize", required = false) Integer pageSize,
				             @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
    	        
        Query query = new Query(pageSize, pageNumber);

        if (ids != null) query.addCondition(new Query.Condition(ModelTypesTable.colId, Query.Comparator.in, ids));
                
        return dao.select(query);
    }
    
    @PutMapping("/api/modeltypes")
    public List<Object> update(@RequestBody List<ModelTypes> entities) {
    	return dao.update(entities);
    }

    @DeleteMapping("/api/modeltypes")
    public List<Object> delete(@RequestBody List<Object> modeltypesIds) {
    	return dao.delete(modeltypesIds);
    }
}