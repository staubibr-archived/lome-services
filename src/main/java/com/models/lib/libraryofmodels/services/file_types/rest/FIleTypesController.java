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

package com.models.lib.libraryofmodels.services.file_types.rest;

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

import com.models.lib.libraryofmodels.services.contributors.ContributorsManager;
import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsQuery;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.file_types.FileTypesManager;
import com.models.lib.libraryofmodels.services.file_types.model.FileTypes;
import com.models.lib.libraryofmodels.services.file_types.model.FileTypesQuery;
import com.models.lib.libraryofmodels.utils.RESTResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FIleTypesController {

    private final FileTypesManager fileTypesManager;

    @Autowired
    public FIleTypesController(FileTypesManager fileTypesManager) {
        this.fileTypesManager = fileTypesManager;
    }

    @GetMapping("/api/fileTypes/{id}")
    public FileTypes get(@PathVariable(value = "id") Long id) {
        log.info("Getting file_types object with id {}", id);
        return fileTypesManager.get(id);
    }

    @GetMapping("/api/fileTypes")
    public RESTResponse list(@RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
    	
        log.info("Getting file_types object");
        
        FileTypesQuery searchQuery = FileTypesQuery.builder()
										                 .pageSize(pageSize)
										                 .pageNumber(pageNumber)
										                 .build();
        
        Page<FileTypes> results = fileTypesManager.search(searchQuery);
        
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PutMapping("/api/fileTypes")
    public RESTResponse update(@RequestBody List<FileTypes> fileTypes) {
        log.info("Updating file_types");
        fileTypesManager.update(fileTypes);
        return RESTResponse.builder().data(fileTypes).build();
    }

    @DeleteMapping("/api/fileTypes")
    public RESTResponse delete(@RequestBody List<String> fileTypesIds) {
        log.info("Deleting file_types");
        fileTypesManager.delete(fileTypesIds);
        return RESTResponse.builder().build();
    }
    
    @PostMapping("/api/fileTypes")
    public RESTResponse create(@RequestBody FileTypes entity) {
        log.info("Creating a file_type");
        fileTypesManager.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}