package com.models.lib.libraryofmodels.services.contributors.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;
import com.models.lib.libraryofmodels.services.models.model.Model;
import com.models.lib.libraryofmodels.services.models.model.ModelsTable;

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
        return new HashMap<String, String>() {{
            put(ContributorsDbColumn.first_name.name(), entity.getFirst_name());
            put(ContributorsDbColumn.last_name.name(), entity.getLast_name());
            put(ContributorsDbColumn.middle_name.name(), entity.getMiddle_name());
            put(ContributorsDbColumn.email.name(), entity.getEmail());
        }};
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
