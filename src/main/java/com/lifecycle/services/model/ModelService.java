package com.lifecycle.services.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lifecycle.components.Folder;
import com.lifecycle.components.Scratch;
import com.lifecycle.util.Entities;

@Service
public class ModelService {
		
	@Value("${app.folders.models}")
	private String APP_FOLDERS_MODELS;

	@Value("${app.models}")
	private String APP_MODELS;
		
    @Autowired
	public ModelService() throws JsonParseException, JsonMappingException, IOException {

	}
	
    public File List() throws IOException {
    	return new File(APP_MODELS);
    }
    
    public ModelMeta Publish(String sMeta, MultipartFile model) throws Exception {
    	Entities<ModelMeta> models = new Entities<ModelMeta>(APP_MODELS, ModelMeta.class); 
    	
    	ModelMeta meta = models.Make(sMeta);
		
    	if (models.Contains(meta::CompareName)) throw new Exception("Cannot create a new model, the name is already in use.");

		Scratch scratch = new Scratch(APP_FOLDERS_MODELS);
		
    	meta.setUuid(scratch.uuid);
    	meta.setCreated(new Date());

    	models.Add(meta);
    	models.Save();
    	
		scratch.Copy(model, "model.json", false);
		
		return meta;
    }
	
    public void Delete(String uuid) throws Exception {
    	Entities<ModelMeta> models = new Entities<ModelMeta>(APP_MODELS, ModelMeta.class); 

    	ModelMeta meta = models.Get((m) -> m.getUuid().toString().equals(uuid));
    	
    	if (meta == null) throw new Exception("Cannot delete the model, it does not exist.");
    	
    	models.Remove(meta);
    	
    	Folder folder = new Folder(Paths.get(APP_FOLDERS_MODELS, uuid));

    	models.Save();
    	folder.Delete();
    }
    
    public File Get(String uuid) throws IOException {
    	Folder folder = new Folder(APP_FOLDERS_MODELS, uuid);
    	
    	return folder.Get("model.json");
    }
    
    public void Update(String sMeta, MultipartFile model) throws Exception {
    	Entities<ModelMeta> models = new Entities<ModelMeta>(APP_MODELS, ModelMeta.class); 
    	
    	ModelMeta curr = models.Make(sMeta);
    	ModelMeta prev = models.Get(curr::CompareUuid);
    	
    	if (prev == null) throw new Exception("Cannot update the model, it does not exist.");

    	Folder folder = new Folder(APP_FOLDERS_MODELS, prev.getUuid());
    	
    	prev.setCreated(curr.getCreated());
    	prev.setDescription(curr.getDescription());
    	prev.setName(curr.getName());
    	
    	models.Save();

    	if (model != null) folder.Copy(model, "model.json", true);
    }
}
