package com.models.lib.lom.services.nn_model_types_v_tags;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class NNModelTypesVTags {

    private Long id;
    private Long model_type_id;
    private Long tag_id;
}
