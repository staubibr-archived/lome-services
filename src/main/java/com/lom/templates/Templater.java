package com.lom.templates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Templater {

	public static String T_MAIN = "./src/main/resources/templates/t_main.txt";
	public static String T_INSTANCES = "./src/main/resources/templates/t_instances.txt";
	public static String T_RELATIONS = "./src/main/resources/templates/t_relations.txt";
	public static String T_INCLUDES = "./src/main/resources/templates/t_includes.txt";
	public static String T_MAKEFILE = "./src/main/resources/templates/makefile";

    public static String make_n(String template, Map<String, String> map, int n) throws IOException {
        List<String> templates = new ArrayList<String>();
        
    	for (int i = 0; i < n; i++) templates.add(Templater.make(template, map, i));

    	return templates.stream().collect(Collectors.joining(System.lineSeparator()));
    }
    
    public static String make(String template, Map<String, String> map, Integer idx) throws IOException {
        List<String> o_frag = new ArrayList<String>();
    	BufferedReader t_frag = new BufferedReader(new FileReader(template));
        String i_line;

    	while ((i_line = t_frag.readLine()) != null) {
    		List<String> tokens = getTokens(i_line);
    		String o_line = i_line;
    		
    		for (int i = 0; i < tokens.size(); i++) {
    			String key_idx = (idx != null) ? tokens.get(i) + "_" + String.valueOf(idx) : tokens.get(i);
    			
    			if (!map.containsKey(key_idx)) continue;
    			
    			o_line = o_line.replace("${" + tokens.get(i) + "}", map.get(key_idx));
    		}
    		
    		o_frag.add(o_line);    		
        }
    	
    	t_frag.close();
    	
    	return o_frag.stream().collect(Collectors.joining(System.lineSeparator()));
    }
    
    private static List<String> getTokens(String line) {
    	List<String> tokens = new ArrayList<String>();
        int i = line.indexOf("${");
        
        while (i > -1) {
        	i += 2;
            int j = line.indexOf("}", i);
            tokens.add(line.substring(i, j));
            i = line.indexOf("${", j);
        }
        
        return tokens;
    }
}
