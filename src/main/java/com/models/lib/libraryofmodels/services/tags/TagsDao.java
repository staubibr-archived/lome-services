package com.models.lib.libraryofmodels.services.tags;

import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.Dao;

@Repository
public class TagsDao extends Dao<Tags> {
	public TagsDao(TagsTable table) {
		super(table);
	}
}