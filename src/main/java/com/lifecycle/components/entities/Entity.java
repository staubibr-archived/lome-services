package com.lifecycle.components.entities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Entity {

    private UUID uuid;
    private String name;
    private String description;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date created;
    
    public Boolean compareName(Entity m) {
    	return this.getName().equals(m.getName());
    }
    
    public Boolean compareUuid(Entity m) {
    	return this.getUuid().equals(m.getUuid());
    }
	
    public void update(Entity meta) {
    	this.setName(name);
    	this.setDescription(description);
    	this.setCreated(created);
    }
    
	public ObjectNode json() {
    	final JsonNodeFactory factory = JsonNodeFactory.instance;
        
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    	return factory.objectNode()
					  .put("uuid", this.uuid.toString())
					  .put("name", this.name)
					  .put("description", this.description)
					  .put("created", df.format(this.created));
	}
	
	public static Entity fromJson(String sJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		
		return om.readValue(sJson, Entity.class);
	}
}
