package com.lom.services.contributors;

import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;

@Repository
public class ContributorsDao extends Dao<Contributors> {
	public ContributorsDao(ContributorsTable table) {
		super(table);
	}
}
