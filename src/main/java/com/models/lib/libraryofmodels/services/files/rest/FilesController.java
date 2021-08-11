/*package com.models.lib.libraryofmodels.services.contributors.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.lib.libraryofmodels.services.contributors.ContributorsManager;
import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsQuery;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.utils.RESTResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ContributorsController {

    private final ContributorsManager contributorsManager;

    @Autowired
    public ContributorsController(ContributorsManager contributorsManager) {
        this.contributorsManager = contributorsManager;
    }

    @GetMapping("/api/v0/contributors/{id}")
    public Contributor get(@PathVariable(value = "id") Long id) {
        log.info("Getting contributors object with id {}", id);
        return contributorsManager.get(id);
    }

    @GetMapping("/api/v0/contributors")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	
        log.info("Getting contributors object");
        
        ContributorsQuery searchQuery = ContributorsQuery.builder()
										                 .pageSize(pageSize)
										                 .pageNumber(pageNumber)
										                 .build();
        
        Page<Contributor> results = contributorsManager.search(searchQuery);
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }
}*/

package com.models.lib.libraryofmodels.services.files.rest;

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


import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.files.FilesManager;
import com.models.lib.libraryofmodels.services.files.model.Files;
import com.models.lib.libraryofmodels.services.files.model.FilesQuery;
import com.models.lib.libraryofmodels.utils.RESTResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FilesController {

    private final FilesManager filesManager;

    @Autowired
    public FilesController(FilesManager filesManager) {
        this.filesManager = filesManager;
    }

    @GetMapping("/api/files/{id}")
    public Files get(@PathVariable(value = "id") Long id) {
        log.info("Getting files object with id {}", id);
        return filesManager.get(id);
    }

    @GetMapping("/api/files")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	
        log.info("Getting files object");
        
        FilesQuery searchQuery = FilesQuery.builder()
										                 .pageSize(pageSize)
										                 .pageNumber(pageNumber)
										                 .build();
        
        Page<Files> results = filesManager.search(searchQuery);
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/files")
    public RESTResponse update(@RequestBody List<Files> files) {
        log.info("Updating files");
        filesManager.update(files);
        return RESTResponse.builder().data(files).build();
    }

    @DeleteMapping("/api/files")
    public RESTResponse delete(@RequestBody List<String> filesIds) {
        log.info("Deleting files");
        filesManager.delete(filesIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/files")
    public RESTResponse create(@RequestBody Files entity) {
    	log.info("Creating a file");
    	filesManager.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}