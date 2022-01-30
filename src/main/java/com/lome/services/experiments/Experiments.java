package com.lome.services.experiments;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lome.services.contributors.Contributors;
import com.lome.services.files.Files;
import com.lome.services.model_types.ModelTypes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Experiments {

    private Long id;
    private String project_name;
    private String name;
    private String description;
    private Date date_created;
    private Long author_id;
    private Long top_model_type_id;

	private Contributors author;
	
	@JsonProperty("top_model_type")
	private ModelTypes top_model_type;

	@JsonInclude(Include.NON_NULL)
	private List<String> tags;

	@JsonInclude(Include.NON_NULL)
	private List<Files> exp_files;

	@JsonInclude(Include.NON_NULL)
	private List<Files> doc_files;

	@JsonInclude(Include.NON_NULL)
	private List<Files> raw_res_files;

	@JsonInclude(Include.NON_NULL)
	private List<Files> conv_res_files;

	@JsonInclude(Include.NON_NULL)
	private List<Files> viz_files;
}