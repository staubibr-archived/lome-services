package com.lifecycle.services.workflow;

import java.io.File;
import java.io.IOException;
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
import com.components.ZipFile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lifecycle.components.Controller;

@RestController
public class WorkflowController extends Controller {
	
    private final WorkflowService wService;
    
    @Autowired
    public WorkflowController(WorkflowService wService) {
        this.wService = wService;
    }
    
    @PostMapping("/api/workflow/execute")
    public ResponseEntity<byte[]> execute(@RequestPart("uuid") String uuid,
 		   								  @RequestPart(value = "data") List<MultipartFile> data) 
    								   			   throws Exception {    	

    	ZipFile zf = this.wService.ExecuteZip(uuid, data);
		
    	return FilesResponse.build("results.zip", zf.toByteArray());
    }
    
	@PostMapping(path="/api/workflow", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ObjectNode post(@RequestPart("workflow") MultipartFile workflow, 
    					   @RequestPart("meta") String sMeta) throws Exception {
				
    	return this.wService.Publish(sMeta, workflow).Json();
    }

	@DeleteMapping(path="/api/workflow")
    public ResponseEntity<RestResponse> delete(@RequestPart("uuid") String uuid) throws Exception {
    	this.wService.Delete(uuid);
    	
    	return this.handleSuccess();
    }
	
	@GetMapping(path="/api/workflow")
    public ResponseEntity<byte[]> get(@RequestParam("uuid") String uuid) throws Exception {
    	File file = this.wService.Get(uuid);

    	return FilesResponse.build(file);
	}
	
	@GetMapping(path="/api/workflow/list")
    public ResponseEntity<byte[]> get() throws Exception {
    	File file = this.wService.List();

    	return FilesResponse.build(file);
	}
	
	@PutMapping(path="/api/workflow")
    public ResponseEntity<RestResponse> put(@RequestPart("meta") String sMeta, 
    								  		@RequestPart(value = "workflow", required = false) MultipartFile workflow) throws Exception {
    	this.wService.Update(sMeta, workflow);

    	return this.handleSuccess();
	}
}