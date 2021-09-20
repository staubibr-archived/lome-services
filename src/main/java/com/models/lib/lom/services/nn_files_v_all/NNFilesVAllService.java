package com.models.lib.lom.services.nn_files_v_all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.services.Dao;
import com.models.lib.lom.components.services.Service;
import com.models.lib.lom.components.services.Query.Comparator;
import com.models.lib.lom.services.files.FilesService;
import com.models.lib.lom.services.files.FilesTable;

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
