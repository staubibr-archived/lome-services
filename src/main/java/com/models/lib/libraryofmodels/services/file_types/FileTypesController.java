package com.models.lib.libraryofmodels.services.file_types;

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
public class FileTypesController {

    private final Dao<FileTypes> dao;

    @Autowired
    public FileTypesController(Dao<FileTypes> dao) {
        this.dao = dao;
    }

    @GetMapping("/api/fileTypes/{id}")
    public FileTypes get(@PathVariable(value = "id") Long id) {
        return dao.get(id.toString());
    }

    @GetMapping("/api/fileTypes")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	        
        FileTypesQuery query = FileTypesQuery.builder()
							                 .pageSize(pageSize)
							                 .pageNumber(pageNumber)
							                 .build();
        
        Page<FileTypes> results = dao.search(query.ToWhere());
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/fileTypes")
    public RESTResponse update(@RequestBody List<FileTypes> fileTypes) {
    	dao.update(fileTypes);
        return RESTResponse.builder().data(fileTypes).build();
    }

    @DeleteMapping("/api/fileTypes")
    public RESTResponse delete(@RequestBody List<String> fileTypesIds) {
        dao.delete(fileTypesIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/fileTypes")
    public RESTResponse create(@RequestBody FileTypes entity) {
        dao.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}