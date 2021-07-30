package com.models.lib.libraryofmodels.services.contributors;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.contributors.dao.ContributorsDao;
import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsQuery;
import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

@Service
public class ContributorsManager {

    private ContributorsDao contributorsDao;

    @Autowired
    public ContributorsManager(ContributorsDao contributorsDao){
        this.contributorsDao = contributorsDao;
    }

    public Contributor get(Long id) {
        return contributorsDao.get(Collections.singletonList(id.toString())).get(0);
    }

    public Page<Contributor> search(ContributorsQuery query) {
        return contributorsDao.search(map(query));
    }

    private DbWhereClause map(ContributorsQuery contributorsQuery) {
        DbWhereClause ret = new DbWhereClause(contributorsQuery.getPageSize(),contributorsQuery.getPageNumber());
        
        if (contributorsQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, contributorsQuery.getIds()));
        }

        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        
        return ret;
    }
}
