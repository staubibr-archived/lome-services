package com.lome.services.nn_model_types_v_tags;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lome.components.Table;

@Component
public class NNModelTypesVTagsTable extends Table<NNModelTypesVTags> {

    public String name() {
        return "nn_model_types_v_tags";
    }

    public static String colId = "id";
	public static String colModelTypeId = "model_type_id";
	public static String colTagId = "tag_id";

	public String pk() {
		return colId;
	}
	
    public List<String> columns() {
		return List.of(colModelTypeId, colTagId);
    }

    public Map<String, Object> mapEntity(NNModelTypesVTags entity) {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put(colId, entity.getId());
        map.put(colModelTypeId, entity.getModel_type_id());
        map.put(colTagId, entity.getTag_id());

        return map;
    }
    
    private static class NNModelTypesVTagsTableEntityMapper implements RowMapper<NNModelTypesVTags> {
        @Override
        public NNModelTypesVTags mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	NNModelTypesVTags res = new NNModelTypesVTags();

            res.setId(resultSet.getLong(colId));
            res.setModel_type_id(resultSet.getLong(colModelTypeId));
            res.setTag_id(resultSet.getLong(colTagId));
           
            return res;
        }
    }

    public RowMapper<NNModelTypesVTags> rowMapper() {
        return new NNModelTypesVTagsTableEntityMapper();
    }
}
