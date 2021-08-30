package com.models.lib.libraryofmodels.services.contributors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ContributorsTable implements Table<Contributors> {

    @Override
    public String name() {
        return "contributors";
    }

    @Override
    public RowMapper<Contributors> rowMapper() {
        return new ContributorsEntityMapper();
    }

    @Override
    public Map<String, String> getParamMap(Contributors entity) {
        Map<String, String> paramMap = new HashMap<String, String>();
        
        insertIntoParamMap(ContributorsDbColumn.id, entity.getId(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.first_name,entity.getFirst_name(),paramMap);
        insertIntoParamMap(ContributorsDbColumn.last_name, entity.getLast_name(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.middle_name, entity.getMiddle_name(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.email,entity.getEmail(),paramMap);
        insertIntoParamMap(ContributorsDbColumn.affiliation, entity.getAffiliation(), paramMap);
        insertIntoParamMap(ContributorsDbColumn.creation_date,entity.getCreation_date(),paramMap);

        return paramMap;
    }
	
    private static class ContributorsEntityMapper implements RowMapper<Contributors> {
        @Override
        public Contributors mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Contributors res = new Contributors();
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
