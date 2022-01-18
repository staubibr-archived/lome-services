package com.lom.services.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;
import com.lom.components.services.Service;

@Repository
public class TagsService extends Service<Tags> {

	@Autowired
	public TagsService(Dao<Tags> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(Tags entity) {

	}
}
