package com.models.lib.libraryofmodels.services.projects;

import lombok.Data;

@Data
public class Project {

    private String id;
    private String name;
    private String description;
    private String creationDate;

    // private Models models; private
}


// add experiment table
// Query models by tags, type

// make projects return models, simulation_results, experiments.