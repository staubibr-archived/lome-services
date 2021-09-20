package com.models.lib.lom.services.file_types;

import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.services.Dao;

@Repository
public class FileTypesDao extends Dao<FileTypes> {
	public FileTypesDao(FileTypesTable table) {
		super(table);
	}
}