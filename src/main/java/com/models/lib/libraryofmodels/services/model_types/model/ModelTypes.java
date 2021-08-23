package com.models.lib.libraryofmodels.services.model_types.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class ModelTypes implements Persistable {

    private Long id;
    private String name;
    private String type;
    private String formalism;
    private String simulator;
    private String description;
    private Date date_created;
    private Long author;
}
