package com.lifecycle.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class Entities<T> {

	private File file;
	private List<T> entities;
	private ObjectMapper mapper = new ObjectMapper();
	private Class<T> type;
	
	public Entities(String s_file, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
		this.file = new File(s_file);
		this.type = type;
		
		if (!this.file.exists()) throw new IOException("Cannot read entities, file " + s_file + " does not exist.");

		CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, this.type);
		
    	this.entities = this.mapper.readValue(this.file, typeReference);    	 	
	} 
	
	public T Make(String s_entity) throws JsonMappingException, JsonProcessingException {
    	return this.mapper.readValue(s_entity, this.type);
	}
	
	public T Add(T entity) {
		this.entities.add(entity);
		
		return entity;
	}
	
	public void Remove(T entity) {
		this.entities.remove(entity);
	}
	
	public void Save() throws JsonGenerationException, JsonMappingException, IOException {
        this.mapper.writeValue(this.file, entities);
	}
	
	public T Get(ICompare<T> fn) {
    	return this.entities.stream()
				   .filter(w -> fn.IsEqual(w))
				   .findAny()
				   .orElse(null);
	}
	
	public Boolean Contains(ICompare<T> fn) {
		T entity = this.Get(fn);
		
		return entity != null;
	}

	public interface ICompare<T> {
		boolean IsEqual(T entity);
	}
}
