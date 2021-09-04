package com.models.lib.lom.services.experiments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Service;

@Repository
public class ExperimentsService extends Service<Experiments> {

	@Autowired
	public ExperimentsService(Dao<Experiments> dao) {
		super(dao);
	}

	@Override
	public Experiments getComplexEntity(Experiments entity) {
		// TODO Auto-generated method stub
		return entity;
	}
}
