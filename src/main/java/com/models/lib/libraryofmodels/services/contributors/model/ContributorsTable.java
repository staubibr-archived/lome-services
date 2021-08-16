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
package com.models.lib.libraryofmodels.services.contributors.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;
import com.models.lib.libraryofmodels.services.experiments.model.ExperimentsTable.ExperimentsDbColumn;


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

    @Override
    public Map<String, String> getParamMap(Contributor entity) {
        Map<String, String> paramMap = new HashMap<String, String>();
        insertIntoParamMap(ContributorsDbColumn.id, entity.getId(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.first_name,entity.getFirst_name(),paramMap);
        insertIntoParamMap(ContributorsDbColumn.last_name, entity.getLast_name(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.middle_name, entity.getMiddle_name(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.email,entity.getEmail(),paramMap);
        insertIntoParamMap(ContributorsDbColumn.affiliation, entity.getAffiliation(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.creation_date,entity.getCreation_date(),paramMap);
//            put(ContributorsDbColumn.id.name(), entity.getId().toString());
//            put(ContributorsDbColumn.first_name.name(), entity.getFirst_name());
//            put(ContributorsDbColumn.last_name.name(), entity.getLast_name());
//            put(ContributorsDbColumn.middle_name.name(), entity.getMiddle_name());
//            put(ContributorsDbColumn.email.name(), entity.getEmail());
//            put(ContributorsDbColumn.affiliation.name(), entity.getAffiliation());
//            put(ContributorsDbColumn.creation_date.name(), entity.getCreation_date().toString());
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

        id(true), first_name(false), last_name(false),
        middle_name(false), email(false), affiliation(false), creation_date(false);

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
