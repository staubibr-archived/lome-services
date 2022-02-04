package com.lifecycle.components.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lifecycle.components.serialization.repeatable.Deserializer;
import com.lifecycle.components.serialization.repeatable.Serializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@JsonPropertyOrder({"identifier", "title", "alternative", "creator", "contributor", "type", "language", 
					"description", "subject", "spatial_coverage", "temporal_coverage", "license", "created", 
					"modified", "time", "behavior", "state", "subcomponent", "coupling", "port", "message"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Model {

	private String identifier;
	
	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> title;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> alternative;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> creator;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> contributor;
	
	private String type;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> language;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> description;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> subject;
	
	@JsonProperty("spatial coverage")
	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = SpatialCoverage.class)
	private List<SpatialCoverage> spatial_coverage;

	@JsonProperty("temporal coverage")
	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = TemporalCoverage.class)
	private List<TemporalCoverage> temporal_coverage;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> license;
	
	private Date created;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> modified;
	
	private String time;

	@JsonSerialize(using = Serializer.class)
	@JsonDeserialize(using = Deserializer.class, contentAs = String.class)
	private List<String> behavior;
	
	private State state;

	@JsonDeserialize(using = Deserializer.class, contentAs = Subcomponent.class)
	private List<Subcomponent> subcomponent;

	@JsonDeserialize(using = Deserializer.class, contentAs = Coupling.class)
	private List<Coupling> coupling;

	@JsonDeserialize(using = Deserializer.class, contentAs = Port.class)
	private List<Port> port;

	@JsonDeserialize(using = Deserializer.class, contentAs = Message.class)
	private List<Message> message;
}

