package com.lome.templates;

import java.util.HashMap;
import java.util.List;

import com.lome.services.workspaces.Instances;
import com.lome.services.workspaces.Relations;

public class TokenMap extends HashMap<String, String> {

	private static final long serialVersionUID = 1L;

	public TokenMap(List<Instances> instances, List<Relations> relations) {
		super();
		
		this.AddInstancesSetTokens(instances);
		this.AddRelationsSetTokens(relations);
	}
	
	public void AddInstanceTokens(Instances instance, int i) {		
		addToken("instances_index", i, String.valueOf(i));
		addToken("source_file", i, instance.getModel_type().getSourceFile().getFullPath());
		addToken("model_type", i, instance.getModel_type().getSourceFile().getClassName());
	}
	
	public void AddRelationsTokens(Relations relations, int i) {		
		addToken("relations_index", i, String.valueOf(i));
		addToken("source_model_type", i, relations.getSource().getModel_type().getSourceFile().getClassName());
		addToken("source_port", i, relations.getSource().getPort());
		addToken("destination_model_type", i, relations.getDestination().getModel_type().getSourceFile().getClassName());
		addToken("destination_port", i, relations.getDestination().getPort());
	}
    
	public void AddRelationsSetTokens(List<Relations> relations) {
    	for (int i = 0; i < relations.size(); i++) this.AddRelationsTokens(relations.get(i), i);
	}
    
	public void AddInstancesSetTokens(List<Instances> instances) {
    	for (int i = 0; i < instances.size(); i++) this.AddInstanceTokens(instances.get(i), i);
	}
	    
    private void addToken(String base, int i, String value) {    	
		this.put(String.format("%s_%d", base, i), value);
    }
}
