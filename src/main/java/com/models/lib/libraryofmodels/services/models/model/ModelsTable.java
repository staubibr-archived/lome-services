package com.models.lib.libraryofmodels.services.models.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.projects.model.Project;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable;
import com.models.lib.libraryofmodels.services.projects.model.Project;

@Component
public class ModelsTable implements Table<Model> {

    @Override
    public String name() {
        return "models";
    }

    @Override
    public RowMapper<Model> rowMapper() {
        return new ResultsEntityMapper();
    }

    @Override
    public Map<String, String> getParamMap(Model entity) {
        return new HashMap<String, String>() {{
            put(ProjectDbColumn.id.name(), entity.getId());
            put(ProjectDbColumn.name.name(), entity.getName());
            put(ProjectDbColumn.type.name(), entity.getType());
            put(ProjectDbColumn.path.name(), entity.getPath());
            put(ProjectDbColumn.project_id.name(), entity.getProjectId());
        }};
    }

    private static class ResultsEntityMapper implements RowMapper<Model> {
        @Override
        public Model mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Model res = new Model();
            res.setId(resultSet.getString(ProjectDbColumn.id.name()));
            res.setName(resultSet.getString(ProjectDbColumn.name.name()));
            res.setType(resultSet.getString(ProjectDbColumn.type.name()));
            res.setPath(resultSet.getString(ProjectDbColumn.path.name()));
            res.setProjectId(resultSet.getString(ProjectDbColumn.project_id.name()));
            return res;
        }
    }

    public enum ProjectDbColumn implements DbColumn {

        id(true), name(false), type(false), path(false), project_id(false);

        private final boolean pkCol;

        ProjectDbColumn(boolean pkCol) {
            this.pkCol = pkCol;
        }

        @Override
        public boolean isPkColumn() {
            return pkCol;
        }
    }

    @Override
    public Class<? extends DbColumn> getTableColumnClass() {
        return ProjectDbColumn.class;
    }
}
