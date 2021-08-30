package com.models.lib.libraryofmodels.services.files;

import org.springframework.stereotype.Repository;

import com.models.lib.libraryofmodels.services.db.Dao;

@Repository
public class FilesDao extends Dao<Files> {
	public FilesDao(FilesTable table) {
		super(table);
	}
}