package com.lome.services.nn_files_v_all;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class NNFilesVAllDao extends Dao<NNFilesVAll> {
	public NNFilesVAllDao(NNFilesVAllTable table) {
		super(table);
	}
}