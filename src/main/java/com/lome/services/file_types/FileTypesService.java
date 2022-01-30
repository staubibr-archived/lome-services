package com.lome.services.file_types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lome.components.Dao;
import com.lome.components.Service;

@Repository
public class FileTypesService extends Service<FileTypes> {

	@Autowired
	public FileTypesService(Dao<FileTypes> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(FileTypes entity) {
		
	}
}
