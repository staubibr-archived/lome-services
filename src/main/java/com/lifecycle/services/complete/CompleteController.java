package com.lifecycle.services.complete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lifecycle.components.Controller;
import com.lifecycle.components.entities.Entity;
import com.lifecycle.components.folders.Folder;
import com.lifecycle.components.folders.UuidFolder;
import com.lifecycle.services.simulation.SimulationService;
import com.lifecycle.services.visualization.VisualizationService;
import com.lifecycle.services.workflow.WorkflowService;

@RestController
public class CompleteController extends Controller {
	
	@Value("${app.folders.scratch}")
	private String APP_FOLDERS_SCRATCH;

    private final WorkflowService wService;
    private final SimulationService sService;
    private final VisualizationService vService;
    
    @Autowired
    public CompleteController(WorkflowService wService, SimulationService sService, VisualizationService vService) {
        this.wService = wService;
        this.sService = sService;
        this.vService = vService;
    }
    
	@PostMapping(path="/api/complete/execute", consumes={ MediaType.MULTIPART_FORM_DATA_VALUE })
    public ObjectNode post(@RequestPart("params") String s_params,
				  		   @RequestPart(value = "workflow_data") List<MultipartFile> data,
				  		   @RequestPart("visualization_config") MultipartFile visualization) throws Exception {
		
		UuidFolder scratch = new UuidFolder(APP_FOLDERS_SCRATCH);

		Folder wFolder = scratch.makeFolder("workflow");
		Folder sFolder = scratch.makeFolder("simulation");
		
		ObjectMapper om = new ObjectMapper();
		CompleteInput params = om.readValue(s_params, CompleteInput.class);
		
		this.wService.Execute(wFolder, params.workflow_uuid.toString(), params.workflow_params);
		
		this.sService.Simulate(sFolder, wFolder.file("output", "auto_coupled.json"), params.simulation_iterations, params.simulation_duration);
		
		Entity meta = Entity.builder().name(params.visualization_name).description(params.visualization_description).build();
		
		meta = this.vService.Create(params.visualization_name, params.visualization_description, visualization, sFolder.file("output", "structure.json"), sFolder.file("output", "messages.log"), wFolder.files("output"));
		
		scratch.delete();
		
		return meta.json();
    }
}