package com.lifecycle.components.entities;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lifecycle.components.serialization.repeatable.Deserializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class SpatialCoverage {
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> placename;
	
	@JsonDeserialize(using = Deserializer.class, contentAs = Extent.class)
	private List<Extent> extent;
}
