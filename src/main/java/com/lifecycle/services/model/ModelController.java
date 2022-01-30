package com.lifecycle.services.model;

import java.io.File;

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
public class ModelController extends Controller {
	
    private final ModelService mService;
    
    @Autowired
    public ModelController(ModelService mService) {
        this.mService = mService;
    }
    
	@PostMapping(path="/api/model", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ObjectNode post(@RequestPart("model") MultipartFile model, 
    					   @RequestPart("meta") String mMeta) throws Exception {
				
    	return this.mService.Publish(mMeta, model).Json();
    }

	@DeleteMapping(path="/api/model")
    public ResponseEntity<RestResponse> delete(@RequestPart("uuid") String uuid) throws Exception {
    	this.mService.Delete(uuid);
    	
    	return this.handleSuccess();
    }
	
	@GetMapping(path="/api/model")
    public ResponseEntity<byte[]> get(@RequestParam("uuid") String uuid) throws Exception {
    	File file = this.mService.Get(uuid);

    	return FilesResponse.build(file);
	}
	
	@GetMapping(path="/api/model/list")
    public ResponseEntity<byte[]> get() throws Exception {
    	File file = this.mService.List();

    	return FilesResponse.build(file);
	}
	
	@PutMapping(path="/api/model")
    public ResponseEntity<RestResponse> put(@RequestPart("meta") String mMeta, 
    								  		@RequestPart(value = "model", required = false) MultipartFile model) throws Exception {
    	this.mService.Update(mMeta, model);

    	return this.handleSuccess();
	}
}