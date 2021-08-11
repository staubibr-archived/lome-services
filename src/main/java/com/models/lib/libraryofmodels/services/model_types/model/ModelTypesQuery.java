package com.models.lib.libraryofmodels.services.model_types.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelTypesQuery {

    private List<Long> ids;
    private Integer pageSize;
    private Integer pageNumber;
}
