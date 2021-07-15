package com.models.lib.libraryofmodels.services.projects;

import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import static com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.models.ModelManager;
import com.models.lib.libraryofmodels.services.models.model.ModelQuery;
import com.models.lib.libraryofmodels.services.projects.dao.ProjectsDao;
import com.models.lib.libraryofmodels.services.projects.model.Project;
import com.models.lib.libraryofmodels.services.projects.model.ProjectQuery;
import com.models.lib.libraryofmodels.services.results.ResultsManager;
import com.models.lib.libraryofmodels.services.results.dao.ResultsDao;
import com.models.lib.libraryofmodels.services.results.model.ResultQuery;
import com.models.lib.libraryofmodels.services.results.model.Results;

@Service
public class ProjectManager {

    private ProjectsDao projectsDao;
    private ResultsManager resultsManager;
    private ModelManager modelManager;

    @Autowired
    public ProjectManager(ProjectsDao projectsDao,
                          ResultsManager resultsManager,
                          ModelManager modelManager){
        this.projectsDao = projectsDao;
        this.resultsManager = resultsManager;
        this.modelManager = modelManager;
    }

    public Project get(String id) {
        return projectsDao.get(Collections.singletonList(id)).get(0);
    }

    public Page<Project> search(ProjectQuery query) {
        Page<Project> data = projectsDao.search(map(query));
        data.getData().forEach(project -> {
            // get results files
            ResultQuery q = ResultQuery.builder().projectId(project.getId()).build();
            project.setResults(resultsManager.search(q).getData());

            // now get models
            ModelQuery modelQuery = ModelQuery.builder().projectId(project.getId()).build();
            project.setModels(modelManager.search(modelQuery).getData());
        });
        return data;
    }

    private DbWhereClause map(ProjectQuery projectQuery) {
        DbWhereClause ret = new DbWhereClause(projectQuery.getPageSize(), projectQuery.getPageNumber());
        if (projectQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, projectQuery.getIds()));
        }
        if (projectQuery.getNames() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.name, Comparator.in, projectQuery.getNames()));
        }
        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        return ret;
    }
}
