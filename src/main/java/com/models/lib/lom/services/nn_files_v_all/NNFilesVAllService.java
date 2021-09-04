package com.models.lib.lom.services.nn_files_v_all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Service;

@Repository
public class NNFilesVAllService extends Service<NNFilesVAll> {

	@Autowired
	public NNFilesVAllService(Dao<NNFilesVAll> dao) {
		super(dao);
	}

	@Override
	public NNFilesVAll getComplexEntity(NNFilesVAll entity) {
		// TODO Auto-generated method stub
		return entity;
	}
}
