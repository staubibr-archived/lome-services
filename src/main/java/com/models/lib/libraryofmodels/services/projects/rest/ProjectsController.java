package com.models.lib.libraryofmodels.services.projects.rest;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.projects.ProjectManager;
import com.models.lib.libraryofmodels.services.projects.model.Project;
import com.models.lib.libraryofmodels.services.projects.model.ProjectQuery;
import com.models.lib.libraryofmodels.services.results.ResultsManager;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.utils.RESTResponse;

@RestController
@Slf4j
public class ProjectsController {

    private final ProjectManager projectManager;

    @Autowired
    public ProjectsController(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @GetMapping("/api/v0/projects/{id}")
    public Project get(@PathVariable(value = "id") String id) {
        log.info("Getting projects file with id {}", id);
        return projectManager.get(id);
    }

    @GetMapping("/api/v0/projects")
    public RESTResponse list(@RequestParam(value = "names", required = false) String names,
                             @RequestParam(value = "ids", required = false) String ids,
                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) {
        log.info("Getting projects file");
        ProjectQuery searchQuery = ProjectQuery.builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .ids(ids == null ? null : Arrays.asList(ids.split(",")))
                .names(names == null ? null : Arrays.asList(names.split(",")))
                .build();
        Page<Project> results = projectManager.search(searchQuery);
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }
}