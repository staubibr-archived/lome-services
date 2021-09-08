package com.models.lib.lom.services.files;

import com.models.lib.lom.services.contributors.Contributors;
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
		
    private FileTypes file_type;
	 Contributors last_author;
    
    public FilesComplete(Files entity, FileTypes file_types, Contributors author) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setFile_type_id(entity.getFile_type_id());
        this.setLast_modification(entity.getLast_modification());
        this.setLast_author_id(entity.getLast_author_id());
        this.setPath(entity.getPath());

        this.setFile_type(file_types);
        this.setLast_author(author);
    }
}
