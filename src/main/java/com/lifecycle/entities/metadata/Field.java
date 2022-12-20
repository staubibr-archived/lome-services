package com.lifecycle.entities.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Field {
	private String name;
	private String description;
	private String type;
	private String uom;
	private String scalar;
	private Long decimals;
}
