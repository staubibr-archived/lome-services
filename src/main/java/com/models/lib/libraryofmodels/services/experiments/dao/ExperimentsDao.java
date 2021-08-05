package com.models.lib.libraryofmodels.services.experiments.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.contributors.ContributorsManager;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsQuery;
import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.experiments.model.ExperimentsTable;

@Repository
public class ExperimentsDao extends AbstractDao<Experiments> {

    private final ContributorsManager contributorsManager;

    public ExperimentsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                          ExperimentsTable experimentsTable,
                          ContributorsManager contributorsManager) {
        super(namedParameterJdbcTemplate, experimentsTable);
        this.contributorsManager = contributorsManager;
    }

    @Override
    public void setRelated(Experiments entity) {
        ContributorsQuery query = ContributorsQuery.builder()
                .experimentId(entity.getId())
                .pageNumber(0)
                .pageSize(100000)
                .build();
        entity.setContributors(contributorsManager.search(query).getData());
    }
}
