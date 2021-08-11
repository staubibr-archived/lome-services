package com.models.lib.libraryofmodels.services.files;

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
import com.models.lib.libraryofmodels.services.files.dao.FilesDao;
import com.models.lib.libraryofmodels.services.files.model.Files;
import com.models.lib.libraryofmodels.services.files.model.FilesQuery;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

@Service
public class FilesManager {

    private FilesDao filesDao;

    @Autowired
    public FilesManager(FilesDao filesDao){
        this.filesDao = filesDao;
    }

    public Files get(Long id) {
        return filesDao.get(Collections.singletonList(id.toString())).get(0);
    }

    public Page<Files> search(FilesQuery query) {
        return filesDao.search(map(query));
    }

    public void create(Files entity) {
    	filesDao.create(entity);
    }
    public void update(List<Files> files) {
    	filesDao.update(files);
    }

    public void delete(List<String> keys) {
    	filesDao.delete(keys);
    }
    
    private DbWhereClause map(FilesQuery filesQuery) {
        DbWhereClause ret = new DbWhereClause(filesQuery.getPageSize(),filesQuery.getPageNumber());
        
        if (filesQuery.getIds() != null) {
            ret.addCondition(new Condition(ResultsDbColumn.id, Comparator.in, filesQuery.getIds()));
        }

        ret.setPageNumber(0);
        ret.setOffset(0);
        ret.setPageSize(100);
        
        return ret;
    }
}
