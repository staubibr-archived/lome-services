package com.models.lib.lom.services.nn_model_types_v_tags;

import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;

@Repository
public class NNModelTypesVTagsDao extends Dao<NNModelTypesVTags> {
	public NNModelTypesVTagsDao(NNModelTypesVTagsTable table) {
		super(table);
	}
}