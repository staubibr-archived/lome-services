package com.models.lib.libraryofmodels.services.projects.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.results.model.Results;

@Data
@SuperBuilder
public class ResultsWrapper {

    private String type;
    private String author;
    private List<Results> files;
}
