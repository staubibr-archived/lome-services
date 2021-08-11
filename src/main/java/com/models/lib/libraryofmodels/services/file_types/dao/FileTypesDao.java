package com.models.lib.libraryofmodels.services.file_types.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.file_types.model.FileTypes;
import com.models.lib.libraryofmodels.services.file_types.model.FileTypesTable;

@Repository
public class FileTypesDao extends AbstractDao<FileTypes> {

    public FileTypesDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, FileTypesTable fileTypesTable) {
        super(namedParameterJdbcTemplate, fileTypesTable);
    }

    
}
