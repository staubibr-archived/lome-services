package com.lifecycle.services.workflow;

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
import com.lifecycle.components.processes.PythonProcess;

@Service
public class WorkflowService {
	
	@Value("${app.tools.qgs_python}")
	private String PYTHON;
	
	@Value("${app.folders.workflows}")
	private String APP_FOLDERS_WORKFLOWS;
	
	@Value("${app.folders.scratch}")
	private String APP_FOLDERS_SCRATCH;

	@Value("${app.workflows}")
	private String APP_WORKFLOWS;
		
    @Autowired
	public WorkflowService() {

	}
	
    public File List() throws IOException {
    	return new File(APP_WORKFLOWS);
    }
	
    public Entities<Entity> Entities() throws JsonParseException, JsonMappingException, IOException {
    	return new Entities<Entity>(APP_WORKFLOWS, Entity.class); 
    }
    
    public File Get(String uuid) throws IOException {
    	Folder folder = new Folder(APP_FOLDERS_WORKFLOWS, uuid);
    	
    	return folder.file("workflow.json");
    }
    
    public Entity Publish(String sMeta, MultipartFile workflow) throws Exception {
    	Entities<Entity> workflows = new Entities<Entity>(APP_WORKFLOWS, Entity.class); 
    	Entity published = workflows.Add(Entity.fromJson(sMeta));
		Scratch scratch = new Scratch(APP_FOLDERS_WORKFLOWS);
		
		published.setUuid(scratch.uuid);
		workflows.Save();
		scratch.copy(workflow, "workflow.json");
		
		return published;
    }
	
    public void Delete(String uuid) throws Exception {
    	Entities<Entity> workflows = new Entities<Entity>(APP_WORKFLOWS, Entity.class); 

    	workflows.Remove(uuid);
    	
    	Folder folder = new Folder(Paths.get(APP_FOLDERS_WORKFLOWS, uuid));

    	workflows.Save();
    	folder.delete();
    }
    
    public void Update(String sMeta, MultipartFile workflow) throws Exception {
    	Entities<Entity> workflows = new Entities<Entity>(APP_WORKFLOWS, Entity.class); 
    	Entity updated = workflows.Update(Entity.fromJson(sMeta));
    	Folder folder = new Folder(APP_FOLDERS_WORKFLOWS, updated.getUuid());
    	
    	workflows.Save();

    	if (workflow != null) folder.copy(workflow, "workflow.json");
    }
    
	public List<File> Execute(Folder scratch, String uuid, List<MultipartFile> data) throws Exception {		
		PythonProcess p = new PythonProcess(PYTHON);

		return p.execute(scratch, this.Get(uuid), data);
	}
	
	public ZipFile ExecuteZip(String uuid, List<MultipartFile> data) throws Exception {
    	Scratch scratch = new Scratch(APP_FOLDERS_SCRATCH);
    	List<File> files = this.Execute(scratch, uuid, data);
    	ZipFile zf = new ZipFile(files);
		
		scratch.delete();
		
		return zf;
	}
}
