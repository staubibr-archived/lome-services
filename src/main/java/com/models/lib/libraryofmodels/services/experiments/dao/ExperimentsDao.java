package com.models.lib.libraryofmodels.services.experiments.dao;

import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.experiments.model.ExperimentsTable;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable;

@Repository
public class ExperimentsDao extends AbstractDao<Experiments> {

    public ExperimentsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ExperimentsTable experimentsTable) {
        super(namedParameterJdbcTemplate, experimentsTable);
    }

    public Experiments getOne(String id) {
        return get(Collections.singletonList(id)).get(0);
    }
}
