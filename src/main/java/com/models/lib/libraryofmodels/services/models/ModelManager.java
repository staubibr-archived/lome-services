package com.models.lib.libraryofmodels.services.models;

import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import static com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.models.dao.ModelsDao;
import com.models.lib.libraryofmodels.services.models.model.Model;
import com.models.lib.libraryofmodels.services.models.model.ModelQuery;
import com.models.lib.libraryofmodels.services.projects.dao.ProjectsDao;
import com.models.lib.libraryofmodels.services.projects.model.Project;
import com.models.lib.libraryofmodels.services.projects.model.ProjectQuery;
import com.models.lib.libraryofmodels.services.results.ResultsManager;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;

@Service
public class ModelManager {

    private ModelsDao modelsDao;

    @Autowired
    public ModelManager(ModelsDao modelsDao){
        this.modelsDao = modelsDao;
    }

    public Model get(String id) {
        return modelsDao.get(Collections.singletonList(id)).get(0);
    }

    public Page<Model> search(ModelQuery query) {
        Page<Model> data = modelsDao.search(map(query));
        return data;
    }

    private DbWhereClause map(ModelQuery modelQuery) {
        DbWhereClause ret = new DbWhereClause(modelQuery.getPageSize(), modelQuery.getPageNumber());
        if (modelQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, modelQuery.getIds()));
        }
        if (modelQuery.getNames() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.name, Comparator.in, modelQuery.getNames()));
        }
        if (modelQuery.getProjectId() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.project_id, Comparator.in, modelQuery.getProjectId()));
        }
        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        return ret;
    }
}
