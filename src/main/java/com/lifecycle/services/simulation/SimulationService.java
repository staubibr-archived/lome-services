package com.lifecycle.services.simulation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.components.ZipFile;
import com.lifecycle.components.Scratch;

@Service
public class SimulationService {
	
	@Value("${app.tools.cadmium}")
	private String SIMULATOR;
	
	@Value("${app.folders.scratch}")
	private String APP_FOLDERS_SCRATCH;
	
    @Autowired
	public SimulationService() {
    	
	}
    
	public List<File> Simulate(Scratch scratch, MultipartFile f_config, Long iterations, Double duration) throws Exception {		
		scratch.Copy(f_config, "simulation.json", false);
    	
		String command = this.SIMULATOR + " " + scratch.Path("simulation.json").toString() + " " + scratch.folder.toString();

		if (iterations != null) command = command + " " + iterations.toString();

		else if (duration != null) command = command + " " + duration.toString();
		
		int exit = Runtime.getRuntime().exec(command).waitFor();
		
		if (exit != 0) throw new Exception("Unable to execute the simulation.");
		
		List<File> files = scratch.Get("structure.json", "messages.log");
		
		return files;
	}
	
	public ZipFile SimulateZip(MultipartFile f_config, Long iterations, Double duration) throws Exception {
    	Scratch scratch = new Scratch(APP_FOLDERS_SCRATCH);
    	List<File> files = this.Simulate(scratch, f_config, iterations, duration);
    	ZipFile zf = new ZipFile(files);
		
		scratch.Delete();
		
		return zf;
	}
}
