package com.models.lib.libraryofmodels.services.model_types.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ModelTypesTable implements Table<ModelTypes> {

    @Override
    public String name() {
        return "model_types";
    }

    @Override
    public RowMapper<ModelTypes> rowMapper() {
        return new ModelTypesEntityMapper();
    }
    
    @Override
    public Map<String, String> getParamMap(ModelTypes entity) {
         
    	Map<String, String> paramMap = new HashMap<String, String>();

		insertIntoParamMap(ModelTypesDbColumn.id, entity.getId(), paramMap);
		insertIntoParamMap(ModelTypesDbColumn.name, entity.getName(), paramMap);
		insertIntoParamMap(ModelTypesDbColumn.type, entity.getType(), paramMap);
		insertIntoParamMap(ModelTypesDbColumn.formalism,entity.getFormalism(),paramMap);
		insertIntoParamMap(ModelTypesDbColumn.simulator,entity.getSimulator(),paramMap);
		insertIntoParamMap(ModelTypesDbColumn.description,entity.getDescription(),paramMap);
		insertIntoParamMap(ModelTypesDbColumn.date_created,entity.getDate_created(),paramMap);
		insertIntoParamMap(ModelTypesDbColumn.author,entity.getAuthor(),paramMap);
		insertIntoParamMap(ModelTypesDbColumn.file,entity.getFile(),paramMap);
		
//            put(ModelTypesDbColumn.id.name(), entity.getId().toString());
//            put(ModelTypesDbColumn.name.name(), entity.getName());
//            put(ModelTypesDbColumn.type.name(), entity.getType());
//            put(ModelTypesDbColumn.formalism.name(), entity.getFormalism());
//            put(ModelTypesDbColumn.simulator.name(), entity.getSimulator());
//            put(ModelTypesDbColumn.description.name(), entity.getDescription());
//            put(ModelTypesDbColumn.date_created.name(),entity.getDate_created().toString());
//            put(ModelTypesDbColumn.author.name(),entity.getAuthor().toString());
//            put(ModelTypesDbColumn.file.name(),entity.getFile().toString());
		return paramMap;
        
    }
    
    private void insertIntoParamMap(DbColumn key, Long value, Map<String, String> paramMap) {

		if (value != null) {
			paramMap.put(key.name(), value.toString());
		}

	}

	private void insertIntoParamMap(DbColumn key, Date value, Map<String, String> paramMap) {

		if (value != null) {
			paramMap.put(key.name(), value.toString());
		}

	}
	
	private void insertIntoParamMap(DbColumn key, String value, Map<String, String> paramMap) {

		if (value != null) {
			paramMap.put(key.name(), value);
		}

	}
    

    private static class ModelTypesEntityMapper implements RowMapper<ModelTypes> {
        @Override
        public ModelTypes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	ModelTypes  res = new ModelTypes();
            res.setId(resultSet.getLong(ModelTypesDbColumn.id.name()));
            res.setName(resultSet.getString(ModelTypesDbColumn.name.name()));
            res.setType(resultSet.getString(ModelTypesDbColumn.type.name()));
            res.setFormalism(resultSet.getString(ModelTypesDbColumn.formalism.name()));
            res.setSimulator(resultSet.getString(ModelTypesDbColumn.simulator.name()));
            res.setDescription(resultSet.getString(ModelTypesDbColumn.description.name()));
            res.setDate_created(resultSet.getDate(ModelTypesDbColumn.date_created.name()));
            res.setAuthor(resultSet.getLong(ModelTypesDbColumn.author.name()));
            res.setFile(resultSet.getLong(ModelTypesDbColumn.file.name()));
            return res;
        }
    }
    
    

    public enum ModelTypesDbColumn implements DbColumn {

        id(true), name(false), type(false), formalism(false), simulator(false), description(false), date_created(false), author(false), file(false);

        private final boolean pkCol;

        ModelTypesDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return ModelTypesDbColumn.class;
    }
}
