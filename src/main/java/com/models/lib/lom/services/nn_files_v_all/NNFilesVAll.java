package com.models.lib.lom.services.nn_files_v_all;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.models.lib.lom.services.files.Files;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class NNFilesVAll {

    private Long id;
    private Long file_id;
    private Long document_id;
    private Long source_id;
    private Long metadata_id;
    private Long experiment_id;
    private Long raw_result_id;
    private Long converted_result_id;
    private Long visualization_id;
    
    @JsonInclude(Include.NON_NULL)
    private Files file;
}