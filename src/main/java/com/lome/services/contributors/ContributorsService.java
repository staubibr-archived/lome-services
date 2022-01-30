package com.lome.services.contributors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lome.components.Dao;
import com.lome.components.Service;

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
