package com.lom.components.structure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

class Component {
	public String id;
	public int model_type;
}

class MessageType {
	public int id;
	public String description;
	public String name;
	public List<String> template;
}

class Port {
	public int message_type;
	public String name;
	public String type;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ModelType {
	public int id;
    public String name;
    public List<Port> ports;
    public String type;
	public int message_type;
	public List<Integer> components;
	public List<List<Integer>> couplings;
}

public class Structure {
	public String top;
	public String formalism;
	public String simulator;
	public List<ModelType> model_types;
	public List<MessageType> message_types;
	public List<Component> components;
}

