package com.lom.services.simulation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lom.components.FilesResponse;
import com.lom.components.Scratch;
import com.lom.components.ZipFile;
import com.lom.components.structure.Structure;

@RestController
public class SimulationController {

	@Value("${app.folders.scratch}")
	private String SCRATCH;

	@Value("${app.folders.visualization}")
	private String VISUALIZATION;
	
    private final SimulationService sService;
    
    @Autowired
    public SimulationController(SimulationService sService) {
        this.sService = sService;
    }
    
    @PostMapping("/api/simulation/cadmium/cbm")
    public ResponseEntity<byte[]> simulate(@RequestPart("config") MultipartFile f_config,
    								   	   @RequestParam(value = "iterations", required = false) Long n_iterations,
    								   	   @RequestParam(value = "duration", required = false) Double n_duration) 
    								   			   throws JsonParseException, JsonMappingException, IOException, InterruptedException {    	
    	
    	Scratch scratch = new Scratch(SCRATCH);
    	
    	List<File> files = this.sService.Simulate(scratch, f_config, n_iterations, n_duration);
    	ZipFile zf = new ZipFile(files);
    	
    	scratch.Delete();
    	
    	return FilesResponse.build("results.zip", zf.toByteArray());
    }
        
    @PostMapping("/api/publish/gis")
    public ObjectNode devs(@RequestPart("messages") MultipartFile messages,
									   @RequestPart("structure") MultipartFile structure,
									   @RequestPart("visualization") MultipartFile visualization,
									   @RequestPart("data") List<MultipartFile> data) throws IOException {

    	Scratch scratch = new Scratch(VISUALIZATION);

    	scratch.Copy(messages);
    	scratch.Copy(structure);
    	scratch.Copy(visualization);
    	scratch.Copy(data);
    	
    	final JsonNodeFactory factory = JsonNodeFactory.instance;

    	return factory.objectNode().put("uuid", scratch.uuid.toString());
    }
}