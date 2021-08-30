package com.models.lib.libraryofmodels.services.files;

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
public class FilesController {

    private final Dao<Files> dao;

    @Autowired
    public FilesController(Dao<Files> dao) {
        this.dao = dao;
    }

    @GetMapping("/api/files/{id}")
    public Files get(@PathVariable(value = "id") Long id) {
        return dao.get(id.toString());
    }

    @GetMapping("/api/files")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	        
        FilesQuery query = FilesQuery.builder()
					                 .pageSize(pageSize)
					                 .pageNumber(pageNumber)
					                 .build();
        
        Page<Files> results = dao.search(query.ToWhere());
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/files")
    public RESTResponse update(@RequestBody List<Files> files) {
    	dao.update(files);
        return RESTResponse.builder().data(files).build();
    }

    @DeleteMapping("/api/files")
    public RESTResponse delete(@RequestBody List<String> filesIds) {
    	dao.delete(filesIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/files")
    public RESTResponse create(@RequestBody Files entity) {
    	dao.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}