package com.models.lib.libraryofmodels.services.file_types.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class FileTypes implements Persistable {

    private Long id;
    private String description;
    private String extension;
}
