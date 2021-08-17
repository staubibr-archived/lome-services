package com.models.lib.libraryofmodels.services.tags.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.models.lib.libraryofmodels.services.db.Persistable;

@Data
@SuperBuilder
@NoArgsConstructor
public class Tags implements Persistable {

    private Long id;
    private String value;
}
