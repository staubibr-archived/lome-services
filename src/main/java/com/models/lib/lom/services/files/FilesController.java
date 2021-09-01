package com.models.lib.lom.services.files;

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

import com.models.lib.lom.services.db.Dao;
import com.models.lib.lom.services.db.Query;

@RestController
public class FilesController {

    private final Dao<Files> dao;

    @Autowired
    public FilesController(Dao<Files> dao) {
        this.dao = dao;
    }
    
    @PostMapping("/api/files")
    public List<Object> create(@RequestBody List<Files> entities) {
    	return dao.create(entities);
    }
    
    @GetMapping("/api/files/{id}")
    public Files get(@PathVariable(value = "id") Long id) {
    	Query query = new Query();
    	
    	query.addCondition(new Query.Condition(FilesTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return dao.selectOne(query);
    }

    @GetMapping("/api/files")
    public List<Files> list(@RequestParam(value = "ids", required = false) String ids,
    						 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
    	        
    	Query query = new Query(pageSize, pageNumber);

        if (ids != null) query.addCondition(new Query.Condition(FilesTable.colId, Query.Comparator.in, ids));
                
        return dao.select(query);
    }

    @PutMapping("/api/files")
    public List<Object> update(@RequestBody List<Files> entities) {
    	return dao.update(entities);
    }

    @DeleteMapping("/api/files")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return dao.delete(filesIds);
    }
}