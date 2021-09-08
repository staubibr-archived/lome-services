package com.models.lib.lom.services.experiments;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Experiments {

    private Long id;
    private String project_name;
    private String name;
    private String description;
    private Date date_created;
    private Long author;
    private Long top_model_type;
}