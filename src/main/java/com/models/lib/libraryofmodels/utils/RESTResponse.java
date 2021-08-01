package com.models.lib.libraryofmodels.utils;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Pagination;
import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class RESTResponse {

    private List<? extends Persistable> data;
    private Pagination pagination;
}
