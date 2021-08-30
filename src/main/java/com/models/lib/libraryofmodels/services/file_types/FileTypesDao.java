package com.models.lib.libraryofmodels.services.file_types;

import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.Dao;

@Repository
public class FileTypesDao extends Dao<FileTypes> {
	public FileTypesDao(FileTypesTable table) {
		super(table);
	}
}