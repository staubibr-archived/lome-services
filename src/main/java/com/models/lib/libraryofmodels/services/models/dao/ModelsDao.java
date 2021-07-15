package com.models.lib.libraryofmodels.services.models.dao;

import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.models.model.Model;
import com.models.lib.libraryofmodels.services.models.model.ModelsTable;
import com.models.lib.libraryofmodels.services.projects.model.Project;
import com.models.lib.libraryofmodels.services.projects.model.ProjectsTable;

@Repository
public class ModelsDao extends AbstractDao<Model> {

    public ModelsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ModelsTable modelsTable) {
        super(namedParameterJdbcTemplate, modelsTable);
    }

    public Model getOne(String id) {
        return get(Collections.singletonList(id)).get(0);
    }
}
