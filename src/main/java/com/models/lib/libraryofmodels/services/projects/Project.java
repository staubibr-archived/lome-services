package com.models.lib.libraryofmodels.services.projects;

import java.util.List;

import lombok.Data;

import com.models.lib.libraryofmodels.services.models.model.Model;
import com.models.lib.libraryofmodels.services.results.model.Results;

@Data
public class Project {

    private String id;
    private String name;
    private String description;
    private String creationDate;
    private List<Model> models;

    // private Models models; private
}


// add experiment table
// Query models by tags, type

// make projects return models, simulation_results, experiments.