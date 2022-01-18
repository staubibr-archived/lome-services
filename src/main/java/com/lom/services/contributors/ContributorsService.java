package com.lom.services.contributors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;
import com.lom.components.services.Service;

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
