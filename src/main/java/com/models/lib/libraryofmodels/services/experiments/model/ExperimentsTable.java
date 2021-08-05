package com.models.lib.libraryofmodels.services.experiments.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, String> getParamMap(Experiments entity) {
        return new HashMap<String, String>() {{
            put(ExperimentsDbColumn.id.name(), entity.getId());
            put(ExperimentsDbColumn.description.name(), entity.getDescription());
            put(ExperimentsDbColumn.state.name(), entity.getState());
            put(ExperimentsDbColumn.inputs.name(), entity.getInputs());
            put(ExperimentsDbColumn.date.name(), entity.getDate());
        }};
    }

    private static class ResultsEntityMapper implements RowMapper<Experiments> {
        @Override
        public Experiments mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Experiments res = new Experiments();
            res.setId(resultSet.getString(ExperimentsDbColumn.id.name()));
            res.setName(resultSet.getString(ExperimentsDbColumn.name.name()));
            res.setDescription(resultSet.getString(ExperimentsDbColumn.description.name()));
            res.setState(resultSet.getString(ExperimentsDbColumn.state.name()));
            res.setInputs(resultSet.getString(ExperimentsDbColumn.inputs.name()));
            res.setDate(resultSet.getString(ExperimentsDbColumn.date.name()));
            return res;
        }
    }

    public enum ExperimentsDbColumn implements DbColumn {

        id(true), name(false), description(false), state(false), inputs(false), date(false);

        private final boolean pkCol;

        ExperimentsDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return ExperimentsDbColumn.class;
    }
}
