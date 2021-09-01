package com.models.lib.libraryofmodels.services.contributors;

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
public class ContributorsController {

    private final Dao<Contributors> dao;
    
    @Autowired
    public ContributorsController(Dao<Contributors> dao) {        
        this.dao = dao;
    }
    
    @PostMapping("/api/contributors")
    public List<Object> create(@RequestBody List<Contributors> entities) {
    	return dao.create(entities);
    }

    @GetMapping("/api/contributors/{id}")
    public Contributors get(@PathVariable(value = "id") Long id) {
    	Query query = new Query();
    	    	
    	query.addCondition(new Query.Condition(ContributorsTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return dao.selectOne(query);
    }

    @GetMapping("/api/contributors")
    public List<Contributors> list(@RequestParam(value = "ids", required = false) String ids,
	    						   @RequestParam(value = "pageSize", required = false) Integer pageSize,
	                               @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

    	Query query = new Query(pageSize, pageNumber);

        if (ids != null) query.addCondition(new Query.Condition(ContributorsTable.colId, Query.Comparator.in, ids));
        
        return dao.select(query);
    }
    
    @PutMapping("/api/contributors")
    public List<Object> update(@RequestBody List<Contributors> entities) {
    	return dao.update(entities);
    }

    @DeleteMapping("/api/contributors")
    public List<Object> delete(@RequestBody List<Object> contributorIds) {
    	return dao.delete(contributorIds);
    }
}