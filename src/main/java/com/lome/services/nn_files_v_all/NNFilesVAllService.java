package com.lome.services.nn_files_v_all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lome.components.Dao;
import com.lome.components.Service;
import com.lome.components.Query.Comparator;
import com.lome.services.files.FilesService;
import com.lome.services.files.FilesTable;

@Repository
public class NNFilesVAllService extends Service<NNFilesVAll> {

	@Autowired
	private FilesService sFiles;
	
	@Autowired
	public NNFilesVAllService(Dao<NNFilesVAll> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(NNFilesVAll e) {
		e.setFile(sFiles.selectOne(FilesTable.colId, Comparator.eq, e.getFile_id(), true));
	}
}
