package com.models.lib.libraryofmodels.services.projects.dao;

import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.projects.model.Project;
import com.models.lib.libraryofmodels.services.projects.model.ProjectsTable;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable;

@Repository
public class ProjectsDao extends AbstractDao<Project> {

    public ProjectsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ProjectsTable projectsTable) {
        super(namedParameterJdbcTemplate, projectsTable);
    }

    public Project getOne(String id) {
        return get(Collections.singletonList(id)).get(0);
    }
}
