package com.lifecycle.entities.metadata;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class TemporalCoverage {
	private String start;
	private String end;
	private String scheme;
}
