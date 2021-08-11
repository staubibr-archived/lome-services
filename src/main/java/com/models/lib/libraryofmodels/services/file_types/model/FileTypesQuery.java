package com.models.lib.libraryofmodels.services.file_types.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FileTypesQuery {

    private List<Long> ids;
    private Integer pageSize;
    private Integer pageNumber;
}
