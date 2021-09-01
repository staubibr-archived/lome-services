/*package com.models.lib.libraryofmodels.services.contributors.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ContributorsTable implements Table<Contributor> {

    @Override
    public String name() {
        return "contributors";
    }

    @Override
    public RowMapper<Contributor> rowMapper() {
        return new ContributorsEntityMapper();
    }

    private static class ContributorsEntityMapper implements RowMapper<Contributor> {
        @Override
        public Contributor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Contributor res = new Contributor();
            res.setId(resultSet.getLong(ContributorsDbColumn.id));
            res.setFirst_name(resultSet.getString(ContributorsDbColumn.first_name));
            res.setLast_name(resultSet.getString(ContributorsDbColumn.last_name));
            res.setMiddle_name(resultSet.getString(ContributorsDbColumn.middle_name));
            res.setEmail(resultSet.getString(ContributorsDbColumn.email));
            res.setAffiliation(resultSet.getString(ContributorsDbColumn.affiliation));
            res.setCreation_date(resultSet.getDate(ContributorsDbColumn.creation_date));
            return res;
        }
    }

    public enum ContributorsDbColumn implements DbColumn {

        id(true), first_name(true), last_name(true), middle_name(false), email(true), affiliation(false), creation_date(false);

        private final boolean pkCol;

        ContributorsDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return ContributorsDbColumn.class;
    }
}
*/
package com.models.lib.libraryofmodels.services.file_types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class FileTypesTable extends Table<FileTypes> {

    public String name() {
        return "file_types";
    }
    
	static String colId = "id";
	static String colDescription = "description";
	static String colExtension = "extension";
	
	public String pk() {
		return colId;
	}

	public List<String> columns() {
		return List.of(colId, colDescription, colExtension);
	}

    public Map<String, Object> mapEntity(FileTypes entity) {
       Map<String, Object> map = new HashMap<String, Object>();

       map.put(colId, entity.getId());
       map.put(colDescription, entity.getDescription());
       map.put(colExtension, entity.getExtension()); 
       
       return map;
    }
	
    private static class FileTypesEntityMapper implements RowMapper<FileTypes> {
        @Override
        public FileTypes mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	FileTypes res = new FileTypes();
        	
            res.setId(resultSet.getLong(colId));
            res.setDescription(resultSet.getString(colDescription));
            res.setExtension(resultSet.getString(colExtension));
            
            return res;
        }
    }
	
    public RowMapper<FileTypes> rowMapper() {
        return new FileTypesEntityMapper();
    }
}
