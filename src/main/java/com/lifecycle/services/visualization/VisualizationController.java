package com.lifecycle.services.visualization;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.components.FilesResponse;
import com.components.RestResponse;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lifecycle.components.Controller;

@RestController
public class VisualizationController extends Controller {
	
    private final VisualizationService vService;
    
    @Autowired
    public VisualizationController(VisualizationService mService) {
        this.vService = mService;
    }
    
	@PostMapping(path="/api/visualization", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ObjectNode post(@RequestPart("visualization") MultipartFile visualization, 
			    		   @RequestPart("structure") MultipartFile structure, 
			    		   @RequestPart("messages") MultipartFile messages, 
			    		   @RequestPart(value = "data", required = false) List<MultipartFile> data, 
    					   @RequestPart("meta") String vMeta) throws Exception {
				
    	return this.vService.Publish(vMeta, visualization, structure, messages, data).Json();
    }

	@DeleteMapping(path="/api/visualization")
    public ResponseEntity<RestResponse> delete(@RequestPart("uuid") String uuid) throws Exception {
    	this.vService.Delete(uuid);
    	
    	return this.handleSuccess();
    }
	
	@GetMapping(path="/api/visualization")
    public ResponseEntity<byte[]> get(@RequestParam("uuid") String uuid) throws Exception {
    	byte[] files = this.vService.Get(uuid).toByteArray();
    	
    	return FilesResponse.build("visualization.zip", files);
	}
	
	@GetMapping(path="/api/visualization/list")
    public ResponseEntity<byte[]> get() throws Exception {
    	File file = this.vService.List();

    	return FilesResponse.build(file);
	}
	
	@PutMapping(path="/api/visualization")
    public ResponseEntity<RestResponse> put(@RequestPart("meta") String vMeta, 
								 		    @RequestPart(value = "visualization", required = false) MultipartFile visualization, 
								 		    @RequestPart(value = "structure", required = false) MultipartFile structure, 
								 		    @RequestPart(value = "messages", required = false) MultipartFile messages, 
								 		    @RequestPart(value = "data", required = false) List<MultipartFile> data) throws Exception {
    	// TODO: UPDATE ALL CONTENTS FROM FOLDER
    	this.vService.Update(vMeta, visualization, structure, messages, data);

    	return this.handleSuccess();
	}
}