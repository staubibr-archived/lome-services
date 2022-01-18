package com.lom.services.simulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lom.components.FilesResponse;
import com.lom.components.Scratch;

@Service
public class SimulationService {
	
	@Value("${app.folders.scratch}")
	private String FOLDER;
	
	@Value("${app.simulator.cadmium}")
	private String SIMULATOR;
	
    @Autowired
	public SimulationService() {
    	
	}
	
	public List<File> Simulate(Scratch scratch, MultipartFile f_config, Long iterations, Double duration) throws IOException, InterruptedException {
    	Path copy_path = scratch.Copy(f_config);    	
		String command = this.SIMULATOR + " " + copy_path.toString() + " " + scratch.folder.toString();

		if (iterations != null) command = command + " " + iterations.toString();

		else if (duration != null) command = command + " " + duration.toString();
		
		Process process = Runtime.getRuntime().exec(command);

		process.waitFor();

		return scratch.Get("structure.json", "messages.log");
	}
	
	
}
