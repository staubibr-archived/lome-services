package com.models.lib.libraryofmodels.services.projects.model;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.models.lib.libraryofmodels.services.db.Persistable;
import com.models.lib.libraryofmodels.services.models.model.Model;
import com.models.lib.libraryofmodels.services.results.model.Results;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project implements Persistable {

    private String id;
    private String name;
    private String description;
    private String creationDate;
    private List<Model> models;
    private List<ResultsWrapper> results;

}


// add experiment table
// Query models by tags, type

// make projects return models, simulation_results, experiments.