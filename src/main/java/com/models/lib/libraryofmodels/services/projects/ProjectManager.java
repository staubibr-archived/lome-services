package com.models.lib.libraryofmodels.services.projects;

import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import static com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import static com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.Page;
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

    @Autowired
    public ProjectManager(ProjectsDao projectsDao,
                          ResultsManager resultsManager){
        this.projectsDao = projectsDao;
        this.resultsManager = resultsManager;
    }

    public Project get(String id) {
        return projectsDao.get(Collections.singletonList(id)).get(0);
    }

    public Page<Project> search(ProjectQuery query) {
        Page<Project> data = projectsDao.search(map(query));
        data.getData().forEach(project -> {
            ResultQuery q = ResultQuery.builder().projectId(project.getId()).build();
            project.setResults(resultsManager.search(q).getData());
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
