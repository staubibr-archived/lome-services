package com.models.lib.lom.services.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Query.Comparator;
import com.models.lib.lom.components.Service;
import com.models.lib.lom.services.contributors.Contributors;
import com.models.lib.lom.services.contributors.ContributorsService;
import com.models.lib.lom.services.contributors.ContributorsTable;
import com.models.lib.lom.services.file_types.FileTypes;
import com.models.lib.lom.services.file_types.FileTypesService;
import com.models.lib.lom.services.file_types.FileTypesTable;

@Repository
public class FilesService extends Service<Files> {

	@Autowired
    private FileTypesService sFileTypes;

	@Autowired
    private ContributorsService sContributors;

	@Autowired
	public FilesService(Dao<Files> dao) {
		super(dao);
	}
	
	@Override
	public Files getComplexEntity(Files e) {
		FileTypes file_types = sFileTypes.selectOne(FileTypesTable.colId, Comparator.eq, e.getFile_type_id());
		Contributors author = sContributors.selectOne(ContributorsTable.colId, Comparator.eq, e.getLast_author_id());
		
		return new FilesComplete(e, file_types, author);
	}
}
