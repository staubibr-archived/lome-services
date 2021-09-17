package com.models.lib.lom.services.nn_model_types_v_tags;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.models.lib.lom.services.tags.Tags;

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
    
    // @JsonInclude(Include.NON_NULL)
    // private ModelTypes model_type;

    @JsonInclude(Include.NON_NULL)
    private Tags tag;
}
