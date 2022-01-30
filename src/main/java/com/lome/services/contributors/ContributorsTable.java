package com.lome.services.contributors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lome.components.Table;

@Component
public class ContributorsTable extends Table<Contributors> {
	
	public String name() {
	    return "contributors";
	}

	public static String colId = "id";
	public static String colFirstName = "first_name";
	public static String colLastName = "last_name";
	public static String colMiddleName = "middle_name";
	public static String colEmail = "email";
	public static String colAffiliation = "affiliation";
	public static String colCreationDate = "creation_date";
	
	public String pk() {
		return colId;
	}
	
	public List<String> columns() {
		return List.of(colId, colFirstName, colLastName, colMiddleName, colEmail, colAffiliation, colCreationDate);
	}

    public Map<String, Object> mapEntity(Contributors entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put(colId, entity.getId());
        map.put(colFirstName, entity.getFirst_name());
        map.put(colLastName, entity.getLast_name());
        map.put(colMiddleName, entity.getMiddle_name());
        map.put(colEmail, entity.getEmail());
        map.put(colAffiliation, entity.getAffiliation());
        map.put(colCreationDate, entity.getCreation_date());

        return map;
    }
	
    private static class ContributorsEntityMapper implements RowMapper<Contributors> {
        @Override
        public Contributors mapRow(ResultSet resultSet, int rowNum) throws SQLException {        			
            Contributors res = new Contributors();
            
            res.setId(resultSet.getLong(colId));
            res.setFirst_name(resultSet.getString(colFirstName));
            res.setLast_name(resultSet.getString(colLastName));
            res.setMiddle_name(resultSet.getString(colMiddleName));
            res.setEmail(resultSet.getString(colEmail));
            res.setAffiliation(resultSet.getString(colAffiliation));
            res.setCreation_date(resultSet.getDate(colCreationDate));
            
            return res;
        }
    }

    public RowMapper<Contributors> rowMapper() {
        return new ContributorsEntityMapper();
    }
}
