package com.models.lib.libraryofmodels.services.experiments.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ExperimentQuery {

    private List<String> ids;
    private List<String> names;
    private Integer pageSize;
    private Integer pageNumber;
}
