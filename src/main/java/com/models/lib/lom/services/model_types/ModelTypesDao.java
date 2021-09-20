package com.models.lib.lom.services.model_types;

import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.services.Dao;

@Repository
public class ModelTypesDao extends Dao<ModelTypes> {
	public ModelTypesDao(ModelTypesTable table) {
		super(table);
	}
}