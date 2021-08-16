package com.models.lib.libraryofmodels.services.experiments.model;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class Experiments implements Persistable {

    private Long id;
    private String project_name;
    private String name;
    private String description;
    private Date date_created;
    private Long author;
    private Long top_model_type;

}
