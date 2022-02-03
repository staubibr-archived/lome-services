package com.lifecycle.components.processes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.lifecycle.components.folders.Folder;

public class SimulationProcess extends Process {

	public SimulationProcess(String path) throws IOException {		
		super();
		
		this.tool = new File(path);
		this.workspace = new Folder(tool.getParent());		
	}

	public List<File> execute(Folder scratch, InputStream config, Long iterations, Double duration) throws Exception {
		Folder input = new Folder(scratch.path("input"), true);
		Folder output = new Folder(scratch.path("output"), true);
		
		input.copy(config, "simulation.json");
		
		int exit;
		
		if (iterations != null) exit = this.execute(this.tool.toString(), input.file("simulation.json").toString(), output.folder.toString(), iterations.toString());

		else if (duration != null) exit = this.execute(this.tool.toString(), input.file("simulation.json").toString(), output.folder.toString(), duration.toString());
		
		else exit = this.execute(this.tool.toString(), input.file("simulation.json").toString(), output.folder.toString());

		if (exit != 0) throw new Exception("Unable to execute the simulation.");
				
		return output.files();
	}
}
