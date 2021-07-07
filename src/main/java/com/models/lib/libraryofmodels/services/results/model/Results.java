package com.models.lib.libraryofmodels.services.results.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class Results implements Persistable {

    private String id;
    private String name;
    private String type;
    private String path;
    private String projectId;

}
