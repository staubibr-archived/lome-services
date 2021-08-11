package com.models.lib.libraryofmodels.services.experiments;

import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import static com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.experiments.dao.ExperimentsDao;
import com.models.lib.libraryofmodels.services.experiments.model.ExperimentQuery;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.results.ResultsManager;
import com.models.lib.libraryofmodels.services.results.dao.ResultsDao;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;

@Service
public class ExperimentsManager {

    private ExperimentsDao experimentsDao;

    @Autowired
    public ExperimentsManager(ExperimentsDao experimentsDao,
                              ResultsManager resultsManager){
        this.experimentsDao = experimentsDao;
    }

    public Experiments get(Long id) {
        return experimentsDao.get(Collections.singletonList(id.toString())).get(0);
    }

    public Page<Experiments> search(ExperimentQuery query) {
        Page<Experiments> data = experimentsDao.search(map(query));
        /* data.getData().forEach(experiment -> {
            ResultQuery q = ResultQuery.builder().projectId(experiment.getId()).build();
            experiment.setResults(resultsManager.search(q).getData());
        });
        
        */
        return data;
    }
    
    public void create(Experiments entity) {
    	experimentsDao.create(entity);
    }
    public void update(List<Experiments> experiments) {
        experimentsDao.update(experiments);
    }

    public void delete(List<String> keys) {
    	experimentsDao.delete(keys);
    }

    private DbWhereClause map(ExperimentQuery query) {
        DbWhereClause ret = new DbWhereClause(query.getPageSize(), query.getPageNumber());
        if (query.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, query.getIds()));
        }
        if (query.getNames() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.name, Comparator.in, query.getNames()));
        }
        return ret;
    }
    
    
}
