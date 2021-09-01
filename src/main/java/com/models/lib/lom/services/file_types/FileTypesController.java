package com.models.lib.lom.services.file_types;

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
public class FileTypesController {

    private final Dao<FileTypes> dao;

    @Autowired
    public FileTypesController(Dao<FileTypes> dao) {
        this.dao = dao;
    }
    
    @PostMapping("/api/fileTypes")
    public List<Object> create(@RequestBody List<FileTypes> entities) {
        return dao.create(entities);
    }

    @GetMapping("/api/fileTypes/{id}")
    public FileTypes get(@PathVariable(value = "id") Long id) {
    	Query query = new Query();
    	
    	query.addCondition(new Query.Condition(FileTypesTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return dao.selectOne(query);
    }

    @GetMapping("/api/fileTypes")
    public List<FileTypes> list(@RequestParam(value = "ids", required = false) String ids,
							 @RequestParam(value = "pageSize", required = false) Integer pageSize,
				             @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
    	        
    	Query query = new Query(pageSize, pageNumber);

        if (ids != null) query.addCondition(new Query.Condition(FileTypesTable.colId, Query.Comparator.in, ids));
        
        return dao.select(query);
    }

    @PutMapping("/api/fileTypes")
    public List<Object> update(@RequestBody List<FileTypes> entities) {
    	return dao.update(entities);
    }

    @DeleteMapping("/api/fileTypes")
    public List<Object> delete(@RequestBody List<Object> fileTypesIds) {
    	return dao.delete(fileTypesIds);
    }
}