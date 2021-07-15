package com.models.lib.libraryofmodels.services.projects.model;

import java.util.List;

import lombok.Data;

import com.models.lib.libraryofmodels.services.db.Persistable;
import com.models.lib.libraryofmodels.services.models.model.Model;

@Data
public class Project implements Persistable {

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