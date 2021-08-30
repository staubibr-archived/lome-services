package com.models.lib.libraryofmodels.services.experiments;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;
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
public class ExperimentsController {
	
    private final Dao<Experiments> dao;

    @Autowired
    public ExperimentsController(Dao<Experiments> dao) {
        this.dao = dao;
    }

    @GetMapping("/api/experiments/{id}")
    public Experiments get(@PathVariable(value = "id") Long id) {
        return dao.get(id.toString());
    }

    @GetMapping("/api/experiments")
    public RESTResponse list(@RequestParam(value = "names", required = false) String names,
                             @RequestParam(value = "ids", required = false) String ids,
                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) throws AuthenticationException {

        ExperimentsQuery searchQuery = ExperimentsQuery.builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .ids(ids == null ? null : Arrays.asList(ids.split(",")))
                .names(names == null ? null : Arrays.asList(names.split(",")))
                .build();
        
        Page<Experiments> results = dao.search(searchQuery.ToWhere());
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }
    
    @PutMapping("/api/experiments")
    public RESTResponse update(@RequestBody List<Experiments> experiments) {
    	dao.update(experiments);
        return RESTResponse.builder().data(experiments).build();
    }

    @DeleteMapping("/api/experiments")
    public RESTResponse delete(@RequestBody List<String> experimentsIds) {
        dao.delete(experimentsIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/experiments")
    public RESTResponse create(@RequestBody Experiments entity) {
    	dao.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}