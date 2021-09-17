package com.models.lib.lom.services.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Service;

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
