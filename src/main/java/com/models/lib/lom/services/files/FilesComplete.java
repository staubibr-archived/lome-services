package com.models.lib.lom.services.files;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.lib.lom.services.file_types.FileTypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FilesComplete extends Files {
	
	@JsonIgnore
	private Long file_types_id;
	
	@JsonProperty("file_type")
    private FileTypes file_types_obj;
    
    public FilesComplete(Files entity, FileTypes file_types) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setFile_type_id(entity.getFile_type_id());
        this.setLast_modification(entity.getLast_modification());
        this.setLast_author(entity.getLast_author());
        this.setPath(entity.getPath());

        this.setFile_types_obj(file_types);
    }
}
