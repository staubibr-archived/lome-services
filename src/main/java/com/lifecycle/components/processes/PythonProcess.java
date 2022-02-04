package com.lifecycle.components.processes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifecycle.components.folders.Folder;

public class PythonProcess extends Process {
		
	public PythonProcess(String path) throws IOException {		
		super();
		
		this.tool = new File(path);
		this.workspace = new Folder(tool.getParent());		
	}

	public List<File> execute(Folder scratch, File workflow, List<MultipartFile> data, JsonNode params) throws Exception {
		Folder input = new Folder(scratch.path("input"), true);
		Folder output = new Folder(scratch.path("output"), true);

		for (MultipartFile f: data) input.copy(f);
		
		String[] a_command = { this.tool.toString(), workflow.toString(), input.folder.toString(), output.folder.toString() };
		ArrayList<String> command = new ArrayList<>(Arrays.asList(a_command));
				
		if (params != null) {
			ObjectMapper om = new ObjectMapper();
			
		    om.writeValue(input.path("params.json").toFile(), params);
		    command.add(input.path("params.json").toString());
		}
		

		int exit = this.execute(command);
		
		if (exit != 0) throw new Exception("Unable to execute the workflow.");
		
		return output.files().stream().filter((File f) -> !f.getName().equals("workflow.json")).collect(Collectors.toList());
	}
}
