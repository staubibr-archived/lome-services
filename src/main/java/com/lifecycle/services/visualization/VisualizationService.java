package com.lifecycle.services.visualization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.components.ZipFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lifecycle.components.Entities;
import com.lifecycle.components.entities.Entity;
import com.lifecycle.components.folders.Folder;
import com.lifecycle.components.folders.Scratch;

@Service
public class VisualizationService {
		
	@Value("${app.folders.visualizations}")
	private String APP_FOLDERS_VISUALIZATIONS;

	@Value("${app.visualizations}")
	private String APP_VISUALIZATIONS;
		
    @Autowired
	public VisualizationService() {

	}
	
    public File List() throws IOException {
    	return new File(APP_VISUALIZATIONS);
    }
	
    public Entities<Entity> Entities() throws JsonParseException, JsonMappingException, IOException {
    	return new Entities<Entity>(APP_VISUALIZATIONS, Entity.class); 
    }
    
    public ZipFile Get(String uuid) throws IOException {
    	Folder folder = new Folder(APP_FOLDERS_VISUALIZATIONS, uuid);
    	
    	return new ZipFile(folder.files());
    }
    
    // TODO: Two almost identical methods just ot handle File vs MultipartFile.
    public Entity Publish(String sMeta, MultipartFile visualization, MultipartFile structure, MultipartFile messages, List<MultipartFile> data) throws Exception {
    	Entities<Entity> visualizations = new Entities<Entity>(APP_VISUALIZATIONS, Entity.class); 
    	Entity published = visualizations.Add(Entity.fromJson(sMeta));
		Scratch scratch = new Scratch(APP_FOLDERS_VISUALIZATIONS);
		
		published.setUuid(scratch.uuid);
		visualizations.Save();
		
		scratch.copy(visualization, "visualization.json");
		scratch.copy(structure, "structure.json");
		scratch.copy(messages, "messages.log");
		
		for (MultipartFile f: data) scratch.copy(f);
		
		return published;
    }

    public Entity Publish(Entity meta, MultipartFile visualization, File structure, File messages, List<File> data) throws Exception {
    	Entities<Entity> visualizations = new Entities<Entity>(APP_VISUALIZATIONS, Entity.class); 
    	Entity published = visualizations.Add(meta);
		Scratch scratch = new Scratch(APP_FOLDERS_VISUALIZATIONS);
		
		published.setUuid(scratch.uuid);
		visualizations.Save();
		
		scratch.copy(visualization, "visualization.json");
		scratch.copy(structure, "structure.json");
		scratch.copy(messages, "messages.log");

		for (File f: data) scratch.copy(f);
		
		return published;
    }
	
    public void Delete(String uuid) throws Exception {
    	Entities<Entity> visualizations = new Entities<Entity>(APP_VISUALIZATIONS, Entity.class); 

    	visualizations.Remove(uuid);
    	
    	Folder folder = new Folder(Paths.get(APP_FOLDERS_VISUALIZATIONS, uuid));

    	visualizations.Save();
    	folder.delete();
    }
    
    public void Update(String sMeta, MultipartFile visualization, MultipartFile structure, MultipartFile messages, List<MultipartFile> data) throws Exception {
    	Entities<Entity> visualizations = new Entities<Entity>(APP_VISUALIZATIONS, Entity.class); 
    	Entity updated = visualizations.Update(Entity.fromJson(sMeta));
    	Folder folder = new Folder(APP_FOLDERS_VISUALIZATIONS, updated.getUuid());
    	
    	visualizations.Save();

    	if (visualization != null) folder.copy(visualization, "visualization.json");
    	if (structure != null) folder.copy(structure, "structure.json");
    	if (messages != null) folder.copy(messages, "messages.log");

    	if (data != null) for (MultipartFile f: data) folder.copy(f);
    }
}
