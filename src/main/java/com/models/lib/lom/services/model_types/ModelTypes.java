package com.models.lib.lom.services.model_types;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.tags.Tags;

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
    private List<Files> files;

	public Files getSourceFile() {
		String model_name = this.getName().replace(" ", "_").toLowerCase();
	
		for (int i = 0; i < this.getFiles().size(); i++) {
			Files f = this.getFiles().get(i);
						
			if (model_name.equals(f.getClassName())) return f;
		}
	
	    throw new RuntimeException("Unable to find a source file for model type " + model_name);
	}
}
