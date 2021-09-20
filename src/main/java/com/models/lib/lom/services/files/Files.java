package com.models.lib.lom.services.files;

import java.nio.file.Paths;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.file_types.FileTypes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Files {

    private Long id;
    private String name;
    private Long file_type_id;
    private Date last_modification;
    private Long last_author_id;
    private String path;

    @JsonInclude(Include.NON_NULL)
    private FileTypes file_type;
    
    @JsonInclude(Include.NON_NULL)
    private Contributors last_author;
	
	public String getClassName() {
		return this.getName().substring(0, this.getName().lastIndexOf("."));
	}
	
	public String getFullPath() {
		return Paths.get(this.getPath(), this.getName()).toString();
	}
	
	public String getServerPath() {
		return this.getId().toString() + "_" + this.getName();
	}
}
