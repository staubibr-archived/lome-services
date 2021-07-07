package com.models.lib.libraryofmodels.services.results.dao;

import java.util.Collections;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable;

@Repository
public class ResultsDao extends AbstractDao<Results> {

    public ResultsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ResultsTable resultsTable) {
        super(namedParameterJdbcTemplate, resultsTable);
    }

    public Results getOne(String id) {
        return get(Collections.singletonList(id)).get(0);
    }
}
