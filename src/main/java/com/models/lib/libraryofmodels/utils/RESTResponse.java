package com.models.lib.libraryofmodels.utils;

import java.util.List;

import com.models.lib.libraryofmodels.services.db.Pagination;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class RESTResponse {

    private List<?> data;
    private Pagination pagination;
}
