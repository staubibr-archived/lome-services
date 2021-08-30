package com.models.lib.libraryofmodels.services.model_types;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ModelTypes {

    private Long id;
    private String name;
    private String type;
    private String formalism;
    private String simulator;
    private String description;
    private Date date_created;
    private Long author;
}