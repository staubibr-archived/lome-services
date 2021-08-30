package com.models.lib.libraryofmodels.services.contributors;

import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.Dao;

@Repository
public class ContributorsDao extends Dao<Contributors> {
	public ContributorsDao(ContributorsTable table) {
		super(table);
	}
}
