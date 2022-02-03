package com.lifecycle.components.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Subcomponent {
	private String identifier;
	private String model;
}
