package com.models.lib.libraryofmodels.services.contributors;

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
public class ContributorsController {

    private final Dao<Contributors> dao;
    
    @Autowired
    public ContributorsController(Dao<Contributors> dao) {        
        this.dao = dao;
    }

    @GetMapping("/api/contributors/{id}")
    public Contributors get(@PathVariable(value = "id") Long id) {
        return dao.get(id.toString());
    }

    @GetMapping("/api/contributors")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {

    	
        ContributorsQuery searchQuery = ContributorsQuery.builder()
										                 .pageSize(pageSize)
										                 .pageNumber(pageNumber)
										                 .build();
                
        Page<Contributors> results = dao.search(searchQuery.ToWhere());
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/contributors")
    public RESTResponse update(@RequestBody List<Contributors> contributors) {
    	dao.update(contributors);
        return RESTResponse.builder().data(contributors).build();
    }

    @DeleteMapping("/api/contributors")
    public RESTResponse delete(@RequestBody List<String> contributorIds) {
    	dao.delete(contributorIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/contributors")
    public RESTResponse create(@RequestBody Contributors entity) {
    	dao.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}