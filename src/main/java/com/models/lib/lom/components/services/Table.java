package com.models.lib.lom.components.services;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

public abstract class Table<K> {
	
	public abstract String name();
    public abstract RowMapper<K> rowMapper();
    public abstract Map<String, Object> mapEntity(K entity);
    public abstract String pk();
    public abstract List<String> columns();
    
    public Map<String, Object> mapEntityNoPk(K entity) {
    	Map<String, Object> map = mapEntity(entity);
    	
    	map.remove(pk());

    	return map;
    }
}
