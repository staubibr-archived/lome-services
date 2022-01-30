package com.lome.services.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lome.components.Dao;
import com.lome.components.Service;

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
