package com.models.lib.libraryofmodels.services.contributors.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ContributorsQuery {

    private List<Long> ids;
    private String experimentId;
    private Integer pageSize;
    private Integer pageNumber;
}
