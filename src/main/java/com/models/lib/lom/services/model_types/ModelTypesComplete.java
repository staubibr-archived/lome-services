package com.models.lib.lom.services.model_types;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.tags.Tags;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ModelTypesComplete extends ModelTypes {
	
	@JsonIgnore
	private Long author;
	
	@JsonProperty("author")
    private Contributors author_obj;
    private List<String> tags;
    
    public ModelTypesComplete(ModelTypes entity, Contributors author, List<Tags> tags) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setType(entity.getType());
        this.setFormalism(entity.getFormalism());
        this.setSimulator(entity.getSimulator());
        this.setDescription(entity.getDescription());
        this.setDate_created(entity.getDate_created());
        this.setAuthor(entity.getAuthor());

        this.setAuthor_obj(author);
        this.setTags(tags.stream().map(t -> t.getValue()).collect(Collectors.toList()));
    }
}
