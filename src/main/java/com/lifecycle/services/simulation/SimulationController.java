package com.lifecycle.services.simulation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.components.FilesResponse;
import com.components.ZipFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class SimulationController {

	@Value("${app.folders.scratch}")
	private String APP_FOLDERS_SCRATCH;
	
    private final SimulationService sService;
    
    @Autowired
    public SimulationController(SimulationService sService) {
        this.sService = sService;
    }
    
    @PostMapping("/api/simulate")
    public ResponseEntity<byte[]> simulate(@RequestPart("config") MultipartFile f_config,
    								   	   @RequestParam(value = "iterations", required = false) Long n_iterations,
    								   	   @RequestParam(value = "duration", required = false) Double n_duration) 
    								   			   throws Exception {    	

    	ZipFile zf = this.sService.SimulateZip(f_config, n_iterations, n_duration);
		
    	return FilesResponse.build("results.zip", zf.toByteArray());
    }
}