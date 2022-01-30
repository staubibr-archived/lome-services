package com.lome.services.experiments;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class ExperimentsDao extends Dao<Experiments> {
	public ExperimentsDao(ExperimentsTable table) {
		super(table);
	}
}
