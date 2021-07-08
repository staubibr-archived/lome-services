package com.models.lib.libraryofmodels.services.experiments.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Persistable;
import com.models.lib.libraryofmodels.services.results.model.Results;

@Data
@SuperBuilder
@NoArgsConstructor
public class Experiments implements Persistable {

    private String id;
    private String name;
    private String description;
    private String state;
    private String inputs;
    private String date;
    private List<Results> results;

}
