package com.models.lib.lom.services.files;

import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.services.Dao;

@Repository
public class FilesDao extends Dao<Files> {
	public FilesDao(FilesTable table) {
		super(table);
	}
}