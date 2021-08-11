package com.models.lib.libraryofmodels.services.experiments.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsTable.ContributorsDbColumn;
import com.models.lib.libraryofmodels.services.db.Table;

@Component
public class ExperimentsTable implements Table<Experiments> {

    @Override
    public String name() {
        return "experiments";
    }

    @Override
    public RowMapper<Experiments> rowMapper() {
        return new ExperimentsEntityMapper();
    }

    @Override
    public Map<String, String> getParamMap(Experiments entity) {
        return new HashMap<String, String>() {{
            put(ExperimentsDbColumn.id.name(), entity.getId().toString());
            put(ExperimentsDbColumn.project_name.name(), entity.getProject_name());
            put(ExperimentsDbColumn.name.name(), entity.getName());
            put(ExperimentsDbColumn.description.name(), entity.getDescriptor());
            put(ExperimentsDbColumn.date_created.name(), entity.getDate_Created().toString());
            put(ExperimentsDbColumn.author.name(), entity.getAuthor().toString());
            put(ExperimentsDbColumn.top_model_type.name(), entity.getTop_model_type().toString());
        }};
    }
    private static class ExperimentsEntityMapper implements RowMapper<Experiments> {
        @Override
        public Experiments mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Experiments res = new Experiments();
            res.setId(resultSet.getLong(ExperimentsDbColumn.id.name()));
            res.setProject_name(resultSet.getString(ExperimentsDbColumn.project_name.name()));
            res.setProject_name(resultSet.getString(ExperimentsDbColumn.name.name()));
            res.setDescriptor(resultSet.getString(ExperimentsDbColumn.description.name()));
            res.setDate_Created(resultSet.getDate(ExperimentsDbColumn.date_created.name()));
            res.setAuthor(resultSet.getLong(ExperimentsDbColumn.author.name()));
            res.setTop_model_type(resultSet.getLong(ExperimentsDbColumn.top_model_type.name()));
            return res;
        }
    }

    public enum ExperimentsDbColumn implements DbColumn {

        id(true), project_name(false), name(false), description(false), date_created(false), author(false), top_model_type(false);

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
