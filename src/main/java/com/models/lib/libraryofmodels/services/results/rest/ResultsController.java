package com.models.lib.libraryofmodels.services.results.rest;

import java.util.Arrays;
import java.util.Collections;

import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.results.ResultsManager;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.utils.RESTResponse;

@RestController
@Slf4j
public class ResultsController {

    private final ResultsManager resultsManager;

    @Autowired
    public ResultsController(ResultsManager resultsManager) {
        this.resultsManager = resultsManager;
    }

    @GetMapping("/api/results/{id}")
    public Results get(@PathVariable(value = "id") String id) {
        log.info("Getting results file with id {}", id);
        return resultsManager.get(id);
    }

    @GetMapping("/api/results")
    public RESTResponse list(@RequestParam(value = "names", required = false) String names,
                             @RequestParam(value = "ids", required = false) String ids,
                             @RequestHeader(value = "Authorization", required = false) String auth,
                             @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer pageSize,
                             @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber) throws AuthenticationException {
        if (Strings.isNotBlank(auth)) {
            throw new AuthenticationException("Invalid authorization token.");
        }
        log.info("Getting results file");
        ResultQuery searchQuery = ResultQuery.builder()
                .pageSize(pageSize)
                .pageNumber(pageNumber)
                .ids(ids == null ? null : Arrays.asList(ids.split(",")))
                .names(names == null ? null : Arrays.asList(names.split(",")))
                .build();
        Page<Results> results = resultsManager.search(searchQuery);
        return RESTResponse.builder().data(results.getData()).pagination(results.getPagination()).build();
    }

    @PostMapping("/api/results")
    public RESTResponse create(@RequestBody Results entity) {
        resultsManager.create(entity);
        return RESTResponse.builder().data(Collections.singletonList(entity)).build();
    }
}