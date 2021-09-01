package com.models.lib.lom.services.model_types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.lom.services.db.Table;

@Component
public class ModelTypesTable extends Table<ModelTypes> {

    public String name() {
        return "model_types";
    }
    
	static String colId = "id";
	static String colName = "name";
	static String colType = "type";
	static String colFormalism = "formalism";
	static String colSimulator = "simulator";
	static String colDescription = "description";
	static String colDateCreated = "date_created";
	static String colAuthor = "author";
	
	public String pk() {
		return colId;
	}

	public List<String> columns() {
		return List.of(colId, colName, colType, colFormalism, colSimulator, colDescription, colDateCreated, colAuthor);
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
		map.put(colAuthor, entity.getAuthor());

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
            res.setAuthor(resultSet.getLong(colAuthor));
            
            return res;
        }
    }
    
    public RowMapper<ModelTypes> rowMapper() {
        return new ModelTypesEntityMapper();
    }
}
