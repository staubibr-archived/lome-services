package com.lom.services.files;

import org.springframework.stereotype.Repository;

import com.lom.components.services.Dao;

@Repository
public class FilesDao extends Dao<Files> {
	public FilesDao(FilesTable table) {
		super(table);
	}
}