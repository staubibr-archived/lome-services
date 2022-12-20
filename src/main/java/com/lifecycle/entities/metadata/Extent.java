package com.lifecycle.entities.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Extent {
	private String reference;
	
	@JsonProperty("x min")
	private double x_min;
	
	@JsonProperty("x max")
	private double x_max;
	
	@JsonProperty("y min")
	private double y_min;
	
	@JsonProperty("y max")
	private double y_max;
}
