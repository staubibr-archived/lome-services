package com.lifecycle.components.processes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.lifecycle.components.folders.Folder;

public class PythonProcess extends Process {
		
	public PythonProcess(String path) throws IOException {		
		super();
		
		this.tool = new File(path);
		this.workspace = new Folder(tool.getParent());		
	}

	public List<File> execute(Folder scratch, File workflow, List<MultipartFile> data) throws Exception {
		Folder input = new Folder(scratch.path("input"), true);
		Folder output = new Folder(scratch.path("output"), true);

		for (MultipartFile f: data) input.copy(f);
		
		int exit = this.execute(this.tool.toString(), workflow.toString(), input.folder.toString(), output.folder.toString());

		if (exit != 0) throw new Exception("Unable to execute the workflow.");
		
		return output.files().stream().filter((File f) -> !f.getName().equals("workflow.json")).collect(Collectors.toList());
	}
}
