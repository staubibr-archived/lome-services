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
package com.models.lib.libraryofmodels.services.tags.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;


@Component
public class TagsTable implements Table<Tags> {

    @Override
    public String name() {
        return "tags";
    }

    @Override
    public RowMapper<Tags> rowMapper() {
        return new TagsEntityMapper();
    }

    @Override
    public Map<String, String> getParamMap(Tags entity) {
        Map<String, String> paramMap = new HashMap<String, String>();
        insertIntoParamMap(TagsDbColumn.id, entity.getId(), paramMap);
        insertIntoParamMap(TagsDbColumn.value, entity.getValue(), paramMap);
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
	
    private static class TagsEntityMapper implements RowMapper<Tags> {
        @Override
        public Tags mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	Tags res = new Tags();
            res.setId(resultSet.getLong(TagsDbColumn.id.name()));
            res.setValue(resultSet.getString(TagsDbColumn.value.name()));
            return res;
        }
    }

    public enum TagsDbColumn implements DbColumn {

        id(true), value(false);

        private final boolean pkCol;

        TagsDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return DbColumn.class;
    }
}
