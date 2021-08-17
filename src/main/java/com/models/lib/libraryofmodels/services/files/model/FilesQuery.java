package com.models.lib.libraryofmodels.services.files.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class FilesQuery {

    private List<Long> ids;
    private Integer pageSize;
    private Integer pageNumber;
}