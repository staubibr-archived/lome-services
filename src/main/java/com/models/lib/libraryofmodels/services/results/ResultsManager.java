package com.models.lib.libraryofmodels.services.results;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.results.dao.ResultsDao;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;

import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import static com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;
import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;

@Service
public class ResultsManager {

    private ResultsDao resultsDao;

    @Autowired
    public ResultsManager(ResultsDao resultsDao){
        this.resultsDao = resultsDao;
    }

    public void create(Results entity) {
        resultsDao.create(entity);
    }

    public Results get(String id) {
        return resultsDao.get(Collections.singletonList(id)).get(0);
    }

    public Page<Results> search(ResultQuery query) {
        return resultsDao.search(map(query));
    }

    private DbWhereClause map(ResultQuery resultQuery) {
        DbWhereClause ret = new DbWhereClause(resultQuery.getPageSize(), resultQuery.getPageNumber());
        if (resultQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, resultQuery.getIds()));
        }
        if (resultQuery.getNames() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.name, Comparator.in, resultQuery.getNames()));
        }
        if (resultQuery.getProjectId() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.project_id, Comparator.eq, resultQuery.getProjectId()));
        }
        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        return ret;
    }
}
