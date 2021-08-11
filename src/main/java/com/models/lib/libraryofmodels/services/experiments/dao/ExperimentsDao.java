package com.models.lib.libraryofmodels.services.experiments.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.experiments.model.ExperimentsTable;

@Repository
public class ExperimentsDao extends AbstractDao<Experiments> {

    public ExperimentsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ExperimentsTable experimentsTable) {
        super(namedParameterJdbcTemplate, experimentsTable);
    }

    
}
