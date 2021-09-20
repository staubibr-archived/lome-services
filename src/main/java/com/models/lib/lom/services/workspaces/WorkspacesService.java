package com.models.lib.lom.services.workspaces;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.models.lib.lom.components.ZipFile;
import com.models.lib.lom.components.services.Query.Comparator;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.model_types.ModelTypes;
import com.models.lib.lom.services.model_types.ModelTypesService;
import com.models.lib.lom.services.model_types.ModelTypesTable;
import com.models.lib.lom.templates.Templater;
import com.models.lib.lom.templates.TokenMap;

@Service
public class WorkspacesService {

	@Value("${app.folders.files}")
	private String FOLDER;

	private final ModelTypesService mService ;
	
    @Autowired
	public WorkspacesService(ModelTypesService mService) {
		this.mService = mService;
	}
    
    public <T> List<T> readJson(MultipartFile file, Class<T> type) throws JsonParseException, JsonMappingException, IOException {
    	ObjectMapper m_instances = new ObjectMapper();
    	CollectionType t_instances = m_instances.getTypeFactory().constructCollectionType(List.class, type);

    	return m_instances.readValue(file.getBytes(), t_instances);
    }
    
    public byte[] make_workspace(List<Instances> instances_sets, List<Relations> relations_sets) throws IOException {
    	this.LoadFromDb(instances_sets, relations_sets);
    	

        ZipFile zf = new ZipFile().Open();
    	
    	for (int i = 0; i < instances_sets.size(); i++) {
    		List<Files> files = instances_sets.get(i).getModel_type().getFiles();
    		
        	for (int j = 0; j < files.size(); j++) {
            	File source = Paths.get(this.FOLDER, files.get(j).getServerPath()).toFile();
            	String target = files.get(j).getFullPath();

        		zf.WriteFull(target, source);
        	}
    	}
   
    	zf.WriteFull("makefile", Paths.get(Templater.T_MAKEFILE).toFile());
    	zf.WriteFull("main.cpp", this.make_main_file(instances_sets, relations_sets));
        zf.Close();
    	
    	return zf.toByteArray();
    }
    
    private void LoadFromDb(List<Instances> instances_sets, List<Relations> relations_sets) {
    	List<Integer> ids = instances_sets.stream().map(i -> i.getModel_type_id()).collect(Collectors.toList());
    	List<ModelTypes> model_types = this.mService.select(ModelTypesTable.colId, Comparator.in, ids, true);
    	
    	instances_sets.forEach(i -> {
    		i.setModel_type(model_types.stream().filter(m -> m.getId() == i.getModel_type_id()).findFirst().get());
    	});
    	
    	relations_sets.forEach(r -> {
    		r.getSource().setModel_type(model_types.stream().filter(m -> m.getId() == r.getSource().getModel_type_id()).findFirst().get());
    		r.getDestination().setModel_type(model_types.stream().filter(m -> m.getId() == r.getDestination().getModel_type_id()).findFirst().get());
    	});
    }
    
    private byte[] make_main_file(List<Instances> instances_sets, List<Relations> relations_sets) throws IOException {
    	TokenMap map = new TokenMap(instances_sets, relations_sets);
    	Map<String, String> includes = new HashMap<String, String>();
    	    	
    	includes.put("include_sources", Templater.make_n(Templater.T_INCLUDES, map, instances_sets.size()));
    	includes.put("include_instances_sets", Templater.make_n(Templater.T_INSTANCES, map, instances_sets.size()));
    	includes.put("include_relations_sets", Templater.make_n(Templater.T_RELATIONS, map, relations_sets.size()));
    	
    	return Templater.make(Templater.T_MAIN, includes, null).getBytes();
    }
}
