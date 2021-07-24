package com.models.lib.libraryofmodels.services.contributors.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsTable;
import com.models.lib.libraryofmodels.services.db.AbstractDao;

@Repository
public class ContributorsDao extends AbstractDao<Contributor> {

    public ContributorsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ContributorsTable contributorsTable) {
        super(namedParameterJdbcTemplate, contributorsTable);
    }
}
