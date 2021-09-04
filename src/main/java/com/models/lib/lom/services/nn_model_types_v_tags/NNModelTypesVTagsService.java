package com.models.lib.lom.services.nn_model_types_v_tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Service;

@Repository
public class NNModelTypesVTagsService extends Service<NNModelTypesVTags> {

	@Autowired
	public NNModelTypesVTagsService(Dao<NNModelTypesVTags> dao) {
		super(dao);
	}

	@Override
	public NNModelTypesVTags getComplexEntity(NNModelTypesVTags entity) {
		// TODO Auto-generated method stub
		return entity;
	}
}
