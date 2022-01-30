package com.lome.services.tags;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class TagsDao extends Dao<Tags> {
	public TagsDao(TagsTable table) {
		super(table);
	}
}