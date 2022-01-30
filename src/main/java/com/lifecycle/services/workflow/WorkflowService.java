package com.lifecycle.services.workflow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	public WorkflowService() throws JsonParseException, JsonMappingException, IOException {

	}
	
    public File List() throws IOException {
    	return new File(APP_WORKFLOWS);
    }
    
    public WorkflowMeta Publish(String sMeta, MultipartFile workflow) throws Exception {
    	Entities<WorkflowMeta> workflows = new Entities<WorkflowMeta>(APP_WORKFLOWS, WorkflowMeta.class); 
    	
    	WorkflowMeta meta = workflows.Make(sMeta);
		
    	if (workflows.Contains(meta::CompareName)) throw new Exception("Cannot create a new workflow, the name is already in use.");

		Scratch scratch = new Scratch(APP_FOLDERS_WORKFLOWS);
		
    	meta.setUuid(scratch.uuid);
    	meta.setCreated(new Date());

    	workflows.Add(meta);
		workflows.Save();
    	
		scratch.Copy(workflow, "workflow.json", false);
		
		return meta;
    }
	
    public void Delete(String uuid) throws Exception {
    	Entities<WorkflowMeta> workflows = new Entities<WorkflowMeta>(APP_WORKFLOWS, WorkflowMeta.class); 

    	WorkflowMeta meta = workflows.Get((m) -> m.getUuid().toString().equals(uuid));
    	
    	if (meta == null) throw new Exception("Cannot delete the workflow, it does not exist.");
    	
    	workflows.Remove(meta);
    	
    	Folder folder = new Folder(Paths.get(APP_FOLDERS_WORKFLOWS, uuid));

    	workflows.Save();
    	folder.Delete();
    }
    
    public File Get(String uuid) throws IOException {
    	Folder folder = new Folder(APP_FOLDERS_WORKFLOWS, uuid);
    	
    	return folder.Get("workflow.json");
    }
    
    public void Update(String sMeta, MultipartFile workflow) throws Exception {
    	Entities<WorkflowMeta> workflows = new Entities<WorkflowMeta>(APP_WORKFLOWS, WorkflowMeta.class); 
    	
    	WorkflowMeta curr = workflows.Make(sMeta);
    	WorkflowMeta prev = workflows.Get(curr::CompareUuid);
    	
    	if (prev == null) throw new Exception("Cannot update the workflow, it does not exist.");

    	Folder folder = new Folder(APP_FOLDERS_WORKFLOWS, prev.getUuid());
    	
    	prev.setCreated(curr.getCreated());
    	prev.setDescription(curr.getDescription());
    	prev.setName(curr.getName());
    	
    	workflows.Save();

    	if (workflow != null) folder.Copy(workflow, "workflow.json", true);
    }

    
	public List<File> Execute(Scratch scratch, String uuid, List<MultipartFile> data) throws Exception {
		Folder folder = new Folder(APP_FOLDERS_WORKFLOWS, uuid);
		Folder input = new Folder(scratch.Path("input"), true);
		Folder output = new Folder(scratch.Path("output"), true);

		input.Copy(data, false);
		
		String s_workflow = folder.Path("workflow.json").toString();
		
		String command = this.PYTHON + " " + s_workflow + " " + input.folder.toString() + " " + output.folder.toString();
		
		int exit = Runtime.getRuntime().exec(command).waitFor();
		
		if (exit != 0) throw new Exception("Unable to execute the workflow.");
		
		List<File> files = scratch.Get();
		
		return files.stream().filter((File f) -> !f.getName().equals("workflow.json")).collect(Collectors.toList());
	}
	
	public ZipFile ExecuteZip(String uuid, List<MultipartFile> data) throws Exception {
		
    	Scratch scratch = new Scratch(APP_FOLDERS_SCRATCH);
    	List<File> files = this.Execute(scratch, uuid, data);
    	ZipFile zf = new ZipFile(files);
		
		scratch.Delete();
		
		return zf;
	}
}
