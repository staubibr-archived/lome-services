package com.lifecycle.services.complete;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CompleteInput {

	UUID workflow_uuid;
	Long simulation_iterations;
	Double simulation_duration;
	String visualization_name;
	String visualization_description;
}
