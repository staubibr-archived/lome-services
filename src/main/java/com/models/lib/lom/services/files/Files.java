package com.models.lib.lom.services.files;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Files {

    private Long id;
    private String name;
    private Long file_type_id;
    private Date last_modification;
    private Long last_author;
    private String path;
}