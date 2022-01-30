package com.lome.services.files;

import org.springframework.stereotype.Repository;

import com.lome.components.Dao;

@Repository
public class FilesDao extends Dao<Files> {
	public FilesDao(FilesTable table) {
		super(table);
	}
}