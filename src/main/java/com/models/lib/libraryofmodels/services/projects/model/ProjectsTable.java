package com.models.lib.libraryofmodels.services.projects.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.models.lib.libraryofmodels.services.db.Table;
import com.models.lib.libraryofmodels.services.results.model.Results;

@Component
public class ProjectsTable implements Table<Project> {

    @Override
    public String name() {
        return "projects";
    }

    @Override
    public RowMapper<Project> rowMapper() {
        return new ResultsEntityMapper();
    }

    private static class ResultsEntityMapper implements RowMapper<Project> {
        @Override
        public Project mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Project res = new Project();
            res.setId(resultSet.getString(ProjectDbColumn.id.name()));
            res.setName(resultSet.getString(ProjectDbColumn.name.name()));
            res.setDescription(resultSet.getString(ProjectDbColumn.description.name()));
            res.setCreationDate(resultSet.getString(ProjectDbColumn.creation_date.name()));
            return res;
        }
    }

    public enum ProjectDbColumn implements DbColumn {

        id(true), name(false), description(false), creation_date(false);

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
