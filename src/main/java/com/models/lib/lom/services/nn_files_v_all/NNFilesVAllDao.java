package com.models.lib.lom.services.nn_files_v_all;

import org.springframework.stereotype.Repository;

import com.models.lib.lom.services.db.Dao;

@Repository
public class NNFilesVAllDao extends Dao<NNFilesVAll> {
	public NNFilesVAllDao(NNFilesVAllTable table) {
		super(table);
	}
	
	@Override
    public void setRelated(NNFilesVAll entity) {
    	
    }
}