package com.lom.services.tags;

import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;

@Repository
public class TagsDao extends Dao<Tags> {
	public TagsDao(TagsTable table) {
		super(table);
	}
}