package com.lome.services.contributors;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class ContributorsDao extends Dao<Contributors> {
	public ContributorsDao(ContributorsTable table) {
		super(table);
	}
}
