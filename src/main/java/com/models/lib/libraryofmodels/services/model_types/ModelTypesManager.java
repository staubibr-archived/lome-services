package com.models.lib.libraryofmodels.services.model_types;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import com.models.lib.libraryofmodels.services.experiments.model.Experiments;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.model_types.dao.ModelTypesDao;
import com.models.lib.libraryofmodels.services.model_types.model.ModelTypes;
import com.models.lib.libraryofmodels.services.model_types.model.ModelTypesQuery;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;


@Service
public class ModelTypesManager {

    private ModelTypesDao modelTypesDao ;

    @Autowired
    public ModelTypesManager(ModelTypesDao modelTypesDao){
        this.modelTypesDao = modelTypesDao;
    }

    public ModelTypes get(Long id) {
        return modelTypesDao.get(Collections.singletonList(id.toString())).get(0);
    }

    public Page<ModelTypes> search(ModelTypesQuery query) {
        return modelTypesDao.search(map(query));
    }
    
    public void create(ModelTypes entity) {
    	modelTypesDao.create(entity);
    }
    
    public void update(List<ModelTypes> modelTypes) {
        modelTypesDao.update(modelTypes);
    }

    public void delete(List<String> keys) {
    	modelTypesDao.delete(keys);
    }

    private DbWhereClause map(ModelTypesQuery modelTypesQuery ) {
        DbWhereClause ret = new DbWhereClause(modelTypesQuery.getPageSize(),modelTypesQuery.getPageNumber());
        
        if (modelTypesQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, modelTypesQuery.getIds()));
        }

        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        
        return ret;
    }
}
