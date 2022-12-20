package com.lifecycle.entities.metadata;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Coupling {
	private String from_model;
	private String from_port;
	private String to_model;
	private String to_port;
}
