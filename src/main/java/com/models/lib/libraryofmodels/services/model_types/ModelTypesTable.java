package com.models.lib.libraryofmodels.services.model_types;

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

		return paramMap;
        
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
            return res;
        }
    }
    
    

    public enum ModelTypesDbColumn implements DbColumn {

        id(true), name(false), type(false), formalism(false), simulator(false), description(false), date_created(false), author(false);

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
