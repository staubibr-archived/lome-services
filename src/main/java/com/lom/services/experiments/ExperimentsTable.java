package com.lom.services.experiments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lom.components.services.Table;

@Component
public class ExperimentsTable extends Table<Experiments> {

	public String name() {
		return "experiments";
	}

	public static String colId = "id";
	public static String colProjectName = "project_name";
	public static String colName = "name";
	public static String colDescription = "description";
	public static String colDateCreated = "date_created";
	public static String colAuthor = "author";
	public static String colTopModelType = "top_model_type";
	
	public String pk() {
		return colId;
	}

	public List<String> columns() {
		return List.of(colId, colProjectName, colName, colDescription, colDateCreated, colAuthor, colTopModelType);
	}

	public Map<String, Object> mapEntity(Experiments entity) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(colId, entity.getId());
		map.put(colProjectName, entity.getProject_name());
		map.put(colName, entity.getName());
		map.put(colDescription, entity.getDescription());
		map.put(colDateCreated, entity.getDate_created());
		map.put(colAuthor, entity.getAuthor_id());
		map.put(colTopModelType, entity.getTop_model_type_id());

		return map;
	}

	private static class ExperimentsEntityMapper implements RowMapper<Experiments> {
		@Override
		public Experiments mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Experiments res = new Experiments();
            
            res.setId(resultSet.getLong(colId));
            res.setProject_name(resultSet.getString(colProjectName));
            res.setName(resultSet.getString(colName));
            res.setDescription(resultSet.getString(colDescription));
            res.setDate_created(resultSet.getDate(colDateCreated));
            res.setAuthor_id(resultSet.getLong(colAuthor));
            res.setTop_model_type_id(resultSet.getLong(colTopModelType));

			return res;
		}
	}

	public RowMapper<Experiments> rowMapper() {
		return new ExperimentsEntityMapper();
	}
}
