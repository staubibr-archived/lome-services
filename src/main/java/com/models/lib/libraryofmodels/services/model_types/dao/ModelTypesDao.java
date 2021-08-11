package com.models.lib.libraryofmodels.services.model_types.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.AbstractDao;
import com.models.lib.libraryofmodels.services.model_types.model.ModelTypes;
import com.models.lib.libraryofmodels.services.model_types.model.ModelTypesTable;

@Repository
public class ModelTypesDao extends AbstractDao<ModelTypes> {

    public ModelTypesDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ModelTypesTable modelTypesTable ) {
        super(namedParameterJdbcTemplate, modelTypesTable);
    }
}
