package com.models.lib.lom.services.contributors;

import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.services.Dao;

@Repository
public class ContributorsDao extends Dao<Contributors> {
	public ContributorsDao(ContributorsTable table) {
		super(table);
	}
}
