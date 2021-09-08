package com.models.lib.lom.services.model_types;

import java.util.List;

import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.files.Files;
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
		
    private Contributors author;
    private List<Tags> tags;
    private List<Files> files;
    
    public ModelTypesComplete(ModelTypes entity, Contributors author, List<Tags> tags, List<Files> files) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setType(entity.getType());
        this.setFormalism(entity.getFormalism());
        this.setSimulator(entity.getSimulator());
        this.setDescription(entity.getDescription());
        this.setDate_created(entity.getDate_created());
        this.setAuthor_id(entity.getAuthor_id());

        this.setAuthor(author);
        this.setTags(tags);
        this.setFiles(files);
    }
}
