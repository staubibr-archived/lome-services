package com.models.lib.libraryofmodels.services.tags.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TagsQuery {

    private List<Long> ids;
    private Integer pageSize;
    private Integer pageNumber;
}
