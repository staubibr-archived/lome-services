package com.lome.services.file_types;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class FileTypesDao extends Dao<FileTypes> {
	public FileTypesDao(FileTypesTable table) {
		super(table);
	}
}