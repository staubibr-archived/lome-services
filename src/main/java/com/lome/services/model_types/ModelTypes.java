package com.lome.services.model_types;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.lome.services.contributors.Contributors;
import com.lome.services.files.Files;
import com.lome.services.tags.Tags;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ModelTypes {

    private Long id;
    private String name;
    private String type;
    private String formalism;
    private String simulator;
    private String description;
    private Date date_created;
    private Long author_id;
    
    @JsonInclude(Include.NON_NULL)
    private Contributors author;

    @JsonInclude(Include.NON_NULL)
    private List<Tags> tags;

    @JsonInclude(Include.NON_NULL)
    private List<Files> src_files;

    // private List<Files> meta_files;

    @JsonInclude(Include.NON_NULL)
    private JsonNode meta;

    @JsonIgnore
	public Files getSourceFile() {
		String model_name = this.getName().replace(" ", "_").toLowerCase();
	
		for (int i = 0; i < this.getSrc_files().size(); i++) {
			Files f = this.getSrc_files().get(i);
						
			if (model_name.equals(f.getClassName())) return f;
		}
	
	    throw new RuntimeException("Unable to find a source file for model type " + model_name);
	}
}
