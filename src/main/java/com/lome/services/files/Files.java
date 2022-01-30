package com.lome.services.files;

import java.nio.file.Paths;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.lome.services.contributors.Contributors;
import com.lome.services.file_types.FileTypes;

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

	@JsonIgnore
	public String getClassName() {
		return this.getName().substring(0, this.getName().lastIndexOf("."));
	}

	@JsonIgnore
	public String getFullPath() {
		return Paths.get(this.getPath(), this.getName()).toString();
	}

	@JsonIgnore
	public String getServerPath() {
		return this.getId().toString() + "_" + this.getName();
	}
}
