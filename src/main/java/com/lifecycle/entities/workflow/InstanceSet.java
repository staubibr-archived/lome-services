package com.lifecycle.entities.workflow;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class InstanceSet {
	private String table;
	private String model_type;
	private String id_field;
	private String[] properties;

	public InstanceSet() {}

	public String getTable() {
		return this.table;
	}

	public void setTable(String value) { this.table = value; }

	@JsonProperty("model_type")
	public String getModelType() { return this.model_type; }

	@JsonProperty("model_type")
	public void setModelType(String value) { this.model_type = value; }

	@JsonProperty("id_field")
	public String getIdField() { return this.id_field; }

	@JsonProperty("id_field")
	public void setIdField(String value) { this.id_field = value; }

	public String[] getProperties() { return this.properties; }

	public void setProperties(String[] value) { this.properties = value; }
}
