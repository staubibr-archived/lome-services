package com.models.lib.libraryofmodels.services.models.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Model {

    private String id;
    private String name;
    private String type;
    private String path;
}
