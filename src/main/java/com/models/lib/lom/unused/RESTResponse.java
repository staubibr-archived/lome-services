package com.models.lib.lom.unused;

import java.util.List;

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
