package com.models.lib.libraryofmodels.utils;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Pagination;
import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
public class RESTResponse {

    private List<? extends Persistable> data;
    private Pagination pagination;
}
