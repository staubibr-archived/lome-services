package com.lome.services.workspaces;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.lome.services.model_types.ModelTypes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Instances {

	private int model_type_id;
	private ModelTypes model_type;
	List<Instance> instances;

	@Data
    static class Instance {
    	public String id;
    	public JsonNode  params;
    }
}
