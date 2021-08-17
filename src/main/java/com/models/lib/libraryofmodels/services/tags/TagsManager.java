package com.models.lib.libraryofmodels.services.tags;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.contributors.dao.ContributorsDao;
import com.models.lib.libraryofmodels.services.contributors.model.Contributor;
import com.models.lib.libraryofmodels.services.contributors.model.ContributorsQuery;
import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;
import com.models.lib.libraryofmodels.services.tags.dao.TagsDao;
import com.models.lib.libraryofmodels.services.tags.model.Tags;
import com.models.lib.libraryofmodels.services.tags.model.TagsQuery;

@Service
public class TagsManager {

    private TagsDao tagsDao;

    @Autowired
    public TagsManager(TagsDao tagsDao){
        this.tagsDao = tagsDao;
    }

    public Tags get(Long id) {
        return tagsDao.get(Collections.singletonList(id.toString())).get(0);
    }

    public Page<Tags> search(TagsQuery query) {
        return tagsDao.search(map(query));
    }

    public void create(Tags entity) {
    	tagsDao.create(entity);
    }
    public void update(List<Tags> contributors) {
    	tagsDao.update(contributors);
    }

    public void delete(List<String> keys) {
    	tagsDao.delete(keys);
    }
    
    private DbWhereClause map(TagsQuery tagsQuery) {
        DbWhereClause ret = new DbWhereClause(tagsQuery.getPageSize(),tagsQuery.getPageNumber());
        
        if (tagsQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, tagsQuery.getIds()));
        }

        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        
        return ret;
    }
}
