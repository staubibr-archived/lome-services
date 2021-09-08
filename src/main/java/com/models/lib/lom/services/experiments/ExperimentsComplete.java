package com.models.lib.lom.services.experiments;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.model_types.ModelTypes;
import com.models.lib.lom.services.tags.Tags;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExperimentsComplete extends Experiments {

	@JsonIgnore
	private Long author;
	@JsonIgnore
	private Long top_model_type;

	@JsonProperty("author")
	private Contributors author_obj;
	@JsonProperty("top_model_type")
	private ModelTypes top_model_type_obj;

	private List<String> tags;

	private Files json_file;

	private List<Files> document_files;

	private List<Files> raw_result_files;

	private List<Files> converted_result_files;

	private List<Files> visualization_files;

	public ExperimentsComplete(Experiments entity, Contributors author, ModelTypes top_model_type, List<Tags> tags,
			Files json_file, List<Files> document_files, List<Files> raw_result_files,
			List<Files> converted_result_files, List<Files> visualization_files) {
		this.setId(entity.getId());
		this.setProject_name(entity.getProject_name());
		this.setName(entity.getName());
		this.setDescription(entity.getDescription());
		this.setDate_created(entity.getDate_created());
		this.setAuthor(entity.getAuthor());
		this.setTop_model_type(entity.getTop_model_type());

		this.setAuthor_obj(author);
		this.setTop_model_type_obj(top_model_type);

		this.setTags(tags.stream().map(t -> t.getValue()).collect(Collectors.toList()));
		this.setJson_file(json_file);
		this.setDocument_files(document_files);
		this.setRaw_result_files(raw_result_files);
		this.setConverted_result_files(converted_result_files);
		this.setVisualization_files(visualization_files);
	}
}
