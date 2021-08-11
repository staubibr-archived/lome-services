package com.models.lib.libraryofmodels.services.files.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.files.model.Files;
import com.models.lib.libraryofmodels.services.files.model.FilesTable;

@Repository
public class FilesDao extends AbstractDao<Files> {

    public FilesDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, FilesTable filesTable) {
        super(namedParameterJdbcTemplate, filesTable);
    }
}
