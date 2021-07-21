package com.models.lib.libraryofmodels.services.experiments.rest;

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
import com.models.lib.libraryofmodels.services.experiments.ExperimentsManager;
import com.models.lib.libraryofmodels.services.experiments.model.ExperimentQuery;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.results.ResultsManager;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.utils.RESTResponse;

@RestController
@Slf4j
public class ExperimentsController {

    private final ExperimentsManager experimentsManager;

    @Autowired
    public ExperimentsController(ExperimentsManager experimentsManager) {
        this.experimentsManager = experimentsManager;
    }

    @GetMapping("/api/experiments/{id}")
    public Experiments get(@PathVariable(value = "id") String id) {
        log.info("Getting results file with id {}", id);
        return experimentsManager.get(id);
    }

    @GetMapping("/api/experiments")
    public RESTResponse list(@RequestParam(value = "names", required = false) String names,
                             @RequestParam(value = "ids", required = false) String ids,
                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) throws AuthenticationException {
        log.info("Getting results file");
        ExperimentQuery searchQuery = ExperimentQuery.builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .ids(ids == null ? null : Arrays.asList(ids.split(",")))
                .names(names == null ? null : Arrays.asList(names.split(",")))
                .build();
        Page<Experiments> results = experimentsManager.search(searchQuery);
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }
}