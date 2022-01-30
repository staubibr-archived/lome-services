package com.lome.services.experiments;

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

import com.lome.components.Query;

@RestController
public class ExperimentsController {
	
    private final ExperimentsService service;

    @Autowired
    public ExperimentsController(ExperimentsService service) {
        this.service = service;
    }
    
    @PostMapping("/api/experiments")
    public List<Object> create(@RequestBody List<Experiments> entities) {
    	return service.create(entities);
    }

    @GetMapping("/api/experiments/{id}")
    public Experiments get(@PathVariable(value = "id") Long id,
						   @RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	
    	return service.selectOne(ExperimentsTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/experiments")
    public List<Experiments> list(@RequestParam(value = "ids", required = false) List<String> ids,
                             	  @RequestParam(value = "complex", defaultValue = "false") Boolean complex) throws AuthenticationException {

        return service.select(ExperimentsTable.colId, Query.Comparator.in, ids, complex);
    }
        
    @PutMapping("/api/experiments")
    public List<Object> update(@RequestBody List<Experiments> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/experiments")
    public List<Object> delete(@RequestBody List<Object> experimentsIds) {
        return service.delete(experimentsIds);
    }
}