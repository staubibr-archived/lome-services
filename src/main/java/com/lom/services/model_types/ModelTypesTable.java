package com.lom.services.model_types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lom.components.services.Table;

@Component
public class ModelTypesTable extends Table<ModelTypes> {

    public String name() {
        return "model_types";
    }
    
    public static String colId = "id";
    public static String colName = "name";
    public static String colType = "type";
    public static String colFormalism = "formalism";
    public static String colSimulator = "simulator";
    public static String colDescription = "description";
    public static String colDateCreated = "date_created";
    public static String colAuthorId = "author";
	
	public String pk() {
		return colId;
	}

	public List<String> columns() {
		return List.of(colId, colName, colType, colFormalism, colSimulator, colDescription, colDateCreated, colAuthorId);
	}
	
    public Map<String, Object> mapEntity(ModelTypes entity) {
    	Map<String, Object> map = new HashMap<String, Object>();

		map.put(colId, entity.getId());
		map.put(colName, entity.getName());
		map.put(colType, entity.getType());
		map.put(colFormalism, entity.getFormalism());
		map.put(colSimulator, entity.getSimulator());
		map.put(colDescription, entity.getDescription());
		map.put(colDateCreated, entity.getDate_created());
		map.put(colAuthorId, entity.getAuthor_id());

		return map;
    }

    private static class ModelTypesEntityMapper implements RowMapper<ModelTypes> {
        @Override
        public ModelTypes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	ModelTypes  res = new ModelTypes();
        	
            res.setId(resultSet.getLong(colId));
            res.setName(resultSet.getString(colName));
            res.setType(resultSet.getString(colType));
            res.setFormalism(resultSet.getString(colFormalism));
            res.setSimulator(resultSet.getString(colSimulator));
            res.setDescription(resultSet.getString(colDescription));
            res.setDate_created(resultSet.getDate(colDateCreated));
            res.setAuthor_id(resultSet.getLong(colAuthorId));
            
            return res;
        }
    }
    
    public RowMapper<ModelTypes> rowMapper() {
        return new ModelTypesEntityMapper();
    }
}
