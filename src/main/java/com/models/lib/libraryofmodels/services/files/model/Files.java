package com.models.lib.libraryofmodels.services.files.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class Files implements Persistable {

    private Long id;
    private String name;
    private Long file_type_id;
    private Date last_modifications;
    private Long last_author;
    private String path;
    private Long model_type_id;
    private Long document_id;
    private Long experiment_id;
    private Long raw_result_id;
    private Long converted_result_id;
    private Long visualization_id;
}
