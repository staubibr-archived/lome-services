package com.models.lib.libraryofmodels.services.results.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ResultsTable implements Table<Results> {

    @Override
    public String name() {
        return "simulation_results";
    }

    @Override
    public RowMapper<Results> rowMapper() {
        return new ResultsEntityMapper();
    }

    private static class ResultsEntityMapper implements RowMapper<Results> {
        @Override
        public Results mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Results res = new Results();
            res.setId(resultSet.getString(ResultsDbColumn.id.name()));
            res.setName(resultSet.getString(ResultsDbColumn.name.name()));
            res.setPath(resultSet.getString(ResultsDbColumn.path.name()));
            res.setType(resultSet.getString(ResultsDbColumn.type.name()));
            res.setProjectId(resultSet.getString(ResultsDbColumn.project_id.name()));
            return res;
        }
    }

    public enum ResultsDbColumn implements DbColumn {

        id(true), name(false), type(false), path(false), project_id(false);

        private final boolean pkCol;

        ResultsDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return ResultsDbColumn.class;
    }
}
