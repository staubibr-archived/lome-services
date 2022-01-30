package com.lome.services.model_types;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class ModelTypesDao extends Dao<ModelTypes> {
	public ModelTypesDao(ModelTypesTable table) {
		super(table);
	}
}