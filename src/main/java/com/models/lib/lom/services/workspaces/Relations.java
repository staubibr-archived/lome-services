package com.models.lib.lom.services.workspaces;

import java.util.List;

import com.models.lib.lom.services.model_types.ModelTypes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Relations {

	private Target source;
	private Target destination;
	private List<List<String>> links;

	@Data
	public static class Target {
		public int model_type_id;
		public ModelTypes model_type;
		public String port;
	}
}
