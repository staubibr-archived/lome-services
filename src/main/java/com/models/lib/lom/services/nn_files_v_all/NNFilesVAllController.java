package com.models.lib.lom.services.nn_files_v_all;

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

import com.models.lib.lom.services.db.Query;

@RestController
public class NNFilesVAllController {

    private final NNFilesVAllDao dao;

    @Autowired
    public NNFilesVAllController(NNFilesVAllDao dao) {
        this.dao = dao;
    }
    
    @PostMapping("/api/tables/nn_files_v_all")
    public List<Object> create(@RequestBody List<NNFilesVAll> entities) {
    	return dao.create(entities);
    }
    
    @GetMapping("/api/tables/nn_files_v_all/{id}")
    public NNFilesVAll get(@PathVariable(value = "id") Long id) {
    	Query query = new Query();
    	
    	query.addCondition(new Query.Condition(NNFilesVAllTable.colId, Query.Comparator.eq, id.toString()));
    	
    	return dao.selectOne(query);
    }

    @GetMapping("/api/tables/nn_files_v_all")
    public List<NNFilesVAll> list(@RequestParam(value = "ids", required = false) String ids,
    						 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
    	        
    	Query query = new Query(pageSize, pageNumber);

        if (ids != null) query.addCondition(new Query.Condition(NNFilesVAllTable.colId, Query.Comparator.in, ids));
                
        return dao.select(query);
    }

    @PutMapping("/api/tables/nn_files_v_all")
    public List<Object> update(@RequestBody List<NNFilesVAll> entities) {
    	return dao.update(entities);
    }

    @DeleteMapping("/api/tables/nn_files_v_all")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return dao.delete(filesIds);
    }
}