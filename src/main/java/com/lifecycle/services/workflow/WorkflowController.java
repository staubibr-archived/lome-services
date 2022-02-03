package com.lifecycle.services.workflow;

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
import org.springframework.web.servlet.ModelAndView;

import com.components.FilesResponse;
import com.components.RestResponse;
import com.components.ZipFile;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lifecycle.components.Controller;
import com.lifecycle.components.Entities;
import com.lifecycle.components.entities.Entity;

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
		
    	return FilesResponse.build("workflow_results.zip", zf.toByteArray());
    }
    
	@PostMapping(path="/api/workflow", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ObjectNode post(@RequestPart("workflow") MultipartFile workflow, 
    					   @RequestPart("meta") String sMeta) throws Exception {
				
    	return this.wService.Publish(sMeta, workflow).json();
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
	
	@GetMapping(path="/api/workflow/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> get() throws Exception {
    	File file = this.wService.List();

    	return FilesResponse.build(file);
	}

	@GetMapping(path="/api/workflow/list", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getHtml() throws Exception {
		ModelAndView mv = new ModelAndView();
		Entities<Entity> entities = this.wService.Entities();
		
        mv.addObject("entities", entities.entities);
        mv.addObject("title", "Workflows");
        mv.addObject("link", "http://localhost:8080/api/workflow?uuid=");
        mv.setViewName("lifecycle/list");
        
        return mv;
	}
	
	@PutMapping(path="/api/workflow")
    public ResponseEntity<RestResponse> put(@RequestPart("meta") String sMeta, 
    								  		@RequestPart(value = "workflow", required = false) MultipartFile workflow) throws Exception {
    	this.wService.Update(sMeta, workflow);

    	return this.handleSuccess();
	}
}