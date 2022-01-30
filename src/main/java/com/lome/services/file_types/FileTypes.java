package com.lome.services.file_types;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class FileTypes {

    private Long id;
    private String description;
    private String extension;
}