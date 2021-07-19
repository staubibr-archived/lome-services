package com.models.lib.libraryofmodels.services.experiments.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ExperimentsTable implements Table<Experiments> {

    @Override
    public String name() {
        return "experiments";
    }

    @Override
    public RowMapper<Experiments> rowMapper() {
        return new ResultsEntityMapper();
    }

    private static class ResultsEntityMapper implements RowMapper<Experiments> {
        @Override
        public Experiments mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Experiments res = new Experiments();
            res.setId(resultSet.getString(ResultsDbColumn.id.name()));
            res.setName(resultSet.getString(ResultsDbColumn.name.name()));
            res.setDescription(resultSet.getString(ResultsDbColumn.description.name()));
            res.setState(resultSet.getString(ResultsDbColumn.state.name()));
            res.setInputs(resultSet.getString(ResultsDbColumn.inputs.name()));
            res.setDate(resultSet.getString(ResultsDbColumn.date.name()));
            return res;
        }
    }

    public enum ResultsDbColumn implements DbColumn {

        id(true), name(false), description(false), state(false), inputs(false), date(false);

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
