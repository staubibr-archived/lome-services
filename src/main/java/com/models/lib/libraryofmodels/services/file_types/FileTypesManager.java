package com.models.lib.libraryofmodels.services.file_types;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.models.lib.libraryofmodels.services.db.DbWhereClause;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Comparator;
import com.models.lib.libraryofmodels.services.db.DbWhereClause.Condition;
import com.models.lib.libraryofmodels.services.db.Page;
import com.models.lib.libraryofmodels.services.file_types.dao.FileTypesDao;
import com.models.lib.libraryofmodels.services.file_types.model.FileTypes;
import com.models.lib.libraryofmodels.services.file_types.model.FileTypesQuery;
import com.models.lib.libraryofmodels.services.results.model.ResultsTable.ResultsDbColumn;

@Service
public class FileTypesManager {

    private FileTypesDao fileTypesDao;

    @Autowired
    public FileTypesManager(FileTypesDao fileTypesDao){
        this.fileTypesDao = fileTypesDao;
    }

    public FileTypes get(Long id) {
        return fileTypesDao.get(Collections.singletonList(id.toString())).get(0);
    }

    public Page<FileTypes> search(FileTypesQuery query) {
        return fileTypesDao.search(map(query));
    }

    public void create(FileTypes entity) {
    	fileTypesDao.create(entity);
    }
    public void update(List<FileTypes> files) {
    	fileTypesDao.update(files);
    }

    public void delete(List<String> keys) {
    	fileTypesDao.delete(keys);
    }
    
    private DbWhereClause map(FileTypesQuery filesQuery) {
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
