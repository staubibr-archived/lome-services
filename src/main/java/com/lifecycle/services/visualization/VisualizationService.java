package com.lifecycle.services.visualization;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.components.ZipFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lifecycle.components.Folder;
import com.lifecycle.components.Scratch;
import com.lifecycle.util.Entities;

@Service
public class VisualizationService {
		
	@Value("${app.folders.visualizations}")
	private String APP_FOLDERS_VISUALIZATIONS;

	@Value("${app.visualizations}")
	private String APP_VISUALIZATIONS;
		
    @Autowired
	public VisualizationService() throws JsonParseException, JsonMappingException, IOException {

	}
	
    public File List() throws IOException {
    	return new File(APP_VISUALIZATIONS);
    }
    
    public VisualizationMeta Publish(String vMeta, MultipartFile visualization, MultipartFile structure, MultipartFile messages, List<MultipartFile> data) throws Exception {
    	Entities<VisualizationMeta> visualizations = new Entities<VisualizationMeta>(APP_VISUALIZATIONS, VisualizationMeta.class); 
    	
    	VisualizationMeta meta = visualizations.Make(vMeta);
		
    	if (visualizations.Contains(meta::CompareName)) throw new Exception("Cannot create a new visualization, the name is already in use.");

		Scratch scratch = new Scratch(APP_FOLDERS_VISUALIZATIONS);
		
    	meta.setUuid(scratch.uuid);
    	meta.setCreated(new Date());

    	visualizations.Add(meta);
    	visualizations.Save();

		scratch.Copy(visualization, "visualization.json", false);
		scratch.Copy(structure, "structure.json", false);
		scratch.Copy(messages, "messages.log", false);
		scratch.Copy(data, false);
		
		return meta;
    }
	
    public void Delete(String uuid) throws Exception {
    	Entities<VisualizationMeta> visualizations = new Entities<VisualizationMeta>(APP_VISUALIZATIONS, VisualizationMeta.class); 

    	VisualizationMeta meta = visualizations.Get((m) -> m.getUuid().toString().equals(uuid));
    	
    	if (meta == null) throw new Exception("Cannot delete the visualization, it does not exist.");
    	
    	visualizations.Remove(meta);
    	
    	Folder folder = new Folder(Paths.get(APP_FOLDERS_VISUALIZATIONS, uuid));

    	visualizations.Save();
    	folder.Delete();
    }
    
    public ZipFile Get(String uuid) throws IOException {
    	Folder folder = new Folder(APP_FOLDERS_VISUALIZATIONS, uuid);
    	
    	ZipFile zf = new ZipFile(folder.Get());
    	
    	return zf;
    }
    
    public void Update(String vMeta, MultipartFile visualization, MultipartFile structure, MultipartFile messages, List<MultipartFile> data) throws Exception {
    	Entities<VisualizationMeta> visualizations = new Entities<VisualizationMeta>(APP_VISUALIZATIONS, VisualizationMeta.class); 
    	
    	VisualizationMeta curr = visualizations.Make(vMeta);
    	VisualizationMeta prev = visualizations.Get(curr::CompareUuid);
    	
    	if (prev == null) throw new Exception("Cannot update the visualization, it does not exist.");

    	Folder folder = new Folder(APP_FOLDERS_VISUALIZATIONS, prev.getUuid());
    	
    	prev.setCreated(curr.getCreated());
    	prev.setDescription(curr.getDescription());
    	prev.setName(curr.getName());
    	
    	visualizations.Save();

    	if (visualization != null) folder.Copy(visualization, "visualization.json", true);
    	if (structure != null) folder.Copy(structure, "structure.json", true);
    	if (messages != null) folder.Copy(messages, "messages.log", true);
    	if (data != null) folder.Copy(data, true);
    }
}
