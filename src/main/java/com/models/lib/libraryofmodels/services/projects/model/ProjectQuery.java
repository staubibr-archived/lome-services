package com.models.lib.libraryofmodels.services.projects.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ProjectQuery {

    private List<String> ids;
    private List<String> names;
    private Integer pageSize;
    private Integer pageNumber;
}
