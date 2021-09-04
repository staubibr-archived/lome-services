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

import com.models.lib.lom.components.Query;

@RestController
public class FileTypesController {

    private final FileTypesService service;

    @Autowired
    public FileTypesController(FileTypesService service) {
        this.service = service;
    }
    
    @PostMapping("/api/fileTypes")
    public List<Object> create(@RequestBody List<FileTypes> entities) {
        return service.create(entities);
    }

    @GetMapping("/api/fileTypes/{id}")
    public FileTypes get(@PathVariable(value = "id") Long id,
			  			 @RequestParam(value = "complex", required = false) Boolean complex) {
    	    	
    	return service.selectOne(FileTypesTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/fileTypes")
    public List<FileTypes> list(@RequestParam(value = "ids", required = false) List<String> ids,
							 	@RequestParam(value = "pageSize", required = false) Integer pageSize,
							 	@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
	   						  	@RequestParam(value = "complex", required = false) Boolean complex) {
        
        return service.select(FileTypesTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/fileTypes")
    public List<Object> update(@RequestBody List<FileTypes> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/fileTypes")
    public List<Object> delete(@RequestBody List<Object> fileTypesIds) {
    	return service.delete(fileTypesIds);
    }
}