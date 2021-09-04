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

import com.models.lib.lom.components.Query;

@RestController
public class NNFilesVAllController {

    private final NNFilesVAllService service;

    @Autowired
    public NNFilesVAllController(NNFilesVAllService service) {
        this.service = service;
    }
    
    @PostMapping("/api/nn_files_v_all")
    public List<Object> create(@RequestBody List<NNFilesVAll> entities) {
    	return service.create(entities);
    }
    
    @GetMapping("/api/nn_files_v_all/{id}")
    public NNFilesVAll get(@PathVariable(value = "id") Long id,
			  			   @RequestParam(value = "complex", required = false) Boolean complex) {
    	    	
    	return service.selectOne(NNFilesVAllTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/nn_files_v_all")
    public List<NNFilesVAll> list(@RequestParam(value = "ids", required = false) List<String> ids,
    						 	  @RequestParam(value = "pageSize", required = false) Integer pageSize,
    						 	  @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
	   						  	  @RequestParam(value = "complex", required = false) Boolean complex) {
    	        
        return service.select(NNFilesVAllTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/nn_files_v_all")
    public List<Object> update(@RequestBody List<NNFilesVAll> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/nn_files_v_all")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return service.delete(filesIds);
    }
}