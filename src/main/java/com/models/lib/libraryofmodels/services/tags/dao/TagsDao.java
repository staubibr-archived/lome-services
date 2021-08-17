package com.models.lib.libraryofmodels.services.tags.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.tags.model.Tags;
import com.models.lib.libraryofmodels.services.tags.model.TagsTable;

@Repository
public class TagsDao extends AbstractDao<Tags> {

    public TagsDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, TagsTable tagsTable) {
        super(namedParameterJdbcTemplate, tagsTable);
    }
}
