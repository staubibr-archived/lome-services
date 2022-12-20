package com.lifecycle.entities.metadata;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lifecycle.components.serialization.repeatable.Deserializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Message {
	private long identifier;
	
	@JsonDeserialize(using = Deserializer.class, contentAs = Field.class)
	private List<Field> field;
}
