package com.models.lib.lom.services.contributors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Service;

@Repository
public class ContributorsService extends Service<Contributors> {
	
	@Autowired
	public ContributorsService(Dao<Contributors> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(Contributors entity) {

	}
}
