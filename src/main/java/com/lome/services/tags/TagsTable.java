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
            res.setId(resultSet.getLong(ContributorsDbColumn.id.name()));
            res.setFirst_name(resultSet.getString(ContributorsDbColumn.first_name.name()));
            res.setLast_name(resultSet.getString(ContributorsDbColumn.last_name.name()));
            res.setMiddle_name(resultSet.getString(ContributorsDbColumn.middle_name.name()));
            res.setEmail(resultSet.getString(ContributorsDbColumn.email.name()));
            res.setAffiliation(resultSet.getString(ContributorsDbColumn.affiliation.name()));
            res.setCreation_date(resultSet.getDate(ContributorsDbColumn.creation_date.name()));
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
package com.lome.services.tags;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.lome.components.Table;


@Component
public class TagsTable extends Table<Tags> {

    public String name() {
        return "tags";
    }

    public static String colId = "id";
	public static String colValue = "value";
	
	public String pk() {
		return colId;
	}

	public List<String> columns() {
		return List.of(colId, colValue);
	}

    public Map<String, Object> mapEntity(Tags entity) {
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put(colId, entity.getId());
        map.put(colValue, entity.getValue());
        
        return map;
    }
	
    private static class TagsEntityMapper implements RowMapper<Tags> {
        @Override
        public Tags mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	Tags res = new Tags();
        	
            res.setId(resultSet.getLong(colId));
            res.setValue(resultSet.getString(colValue));
            
            return res;
        }
    }    
	
    public RowMapper<Tags> rowMapper() {
        return new TagsEntityMapper();
    }
}
