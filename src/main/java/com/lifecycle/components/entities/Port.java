package com.lifecycle.components.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Port {
	private String type;
	private String name;
	private long message;
}
