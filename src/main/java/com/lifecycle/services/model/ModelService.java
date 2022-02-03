package com.lifecycle.services.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifecycle.components.Entities;
import com.lifecycle.components.entities.Entity;
import com.lifecycle.components.entities.Model;
import com.lifecycle.components.folders.Folder;
import com.lifecycle.components.folders.Scratch;

@Service
public class ModelService {
	
	@Value("${app.folders.models}")
	private String APP_FOLDERS_MODELS;
	
	@Value("${app.models}")
	private String APP_MODELS;

    @Autowired
	public ModelService() {

	}
	
    public File List() throws IOException {
    	return new File(APP_MODELS);
    }
	
    public Entities<Entity> Entities() throws JsonParseException, JsonMappingException, IOException {
    	return new Entities<Entity>(APP_MODELS, Entity.class); 
    }
    
    public File GetFile(String uuid) throws IOException {
    	Folder folder = new Folder(APP_FOLDERS_MODELS, uuid);
    	
    	return folder.file("model.json");
    }
    
    public Model GetModel(String uuid) throws IOException {
    	ObjectMapper om = new ObjectMapper();
    	
    	return om.readValue(this.GetFile(uuid), Model.class);
    }
    
    public Entity Publish(String sMeta, MultipartFile model) throws Exception {
    	ObjectMapper om = new ObjectMapper();
    	Model mm = om.readValue(model.getInputStream(), Model.class);
    	
    	Entities<Entity> models = new Entities<Entity>(APP_MODELS, Entity.class); 
    	Entity published = models.Add(Entity.fromJson(sMeta));
		Scratch scratch = new Scratch(APP_FOLDERS_MODELS);
		
		mm.setIdentifier(scratch.uuid.toString());
		published.setUuid(scratch.uuid);
		
    	models.Save();
    	
    	File file = new File(scratch.path("model.json").toString());
    	om.writeValue(file, mm);
		
		return published;
    }
	
    public void Delete(String uuid) throws Exception {
    	Entities<Entity> models = new Entities<Entity>(APP_MODELS, Entity.class); 

    	models.Remove(uuid);
    	
    	Folder folder = new Folder(Paths.get(APP_FOLDERS_MODELS, uuid));

    	models.Save();
    	folder.delete();
    }
	    
    public void Update(String sMeta, MultipartFile model) throws Exception {
    	Entities<Entity> models = new Entities<Entity>(APP_MODELS, Entity.class); 
    	Entity updated = models.Update(Entity.fromJson(sMeta));
    	Folder folder = new Folder(APP_FOLDERS_MODELS, updated.getUuid());
    	
    	models.Save();

    	if (model != null) folder.copy(model, "model.json");
    }
}
