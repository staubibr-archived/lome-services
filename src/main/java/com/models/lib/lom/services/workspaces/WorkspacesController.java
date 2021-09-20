package com.models.lib.lom.services.workspaces;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.models.lib.lom.components.FilesResponse;

@RestController
public class WorkspacesController {

    private final WorkspacesService wService;
    
    @Autowired
    public WorkspacesController(WorkspacesService wService) {
        this.wService = wService;
    }
    
    @PostMapping("/api/workspaces")
    public ResponseEntity<byte[]> create(@RequestPart("instances") MultipartFile f_instances, @RequestPart("relations") MultipartFile f_relations) throws JsonParseException, JsonMappingException, IOException {    	
    	List<Instances> instances = this.wService.readJson(f_instances, Instances.class);
    	List<Relations> relations = this.wService.readJson(f_relations, Relations.class);
    	
    	return FilesResponse.build("workspace.zip", this.wService.make_workspace(instances, relations));
    }
}