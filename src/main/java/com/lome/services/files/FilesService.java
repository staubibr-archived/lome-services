package com.lome.services.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lifecycle.components.ZipFile;
import com.lome.components.Dao;
import com.lome.components.Service;
import com.lome.components.Query.Comparator;
import com.lome.services.contributors.ContributorsService;
import com.lome.services.contributors.ContributorsTable;
import com.lome.services.file_types.FileTypesService;
import com.lome.services.file_types.FileTypesTable;

@Repository
public class FilesService extends Service<Files> {

	@Value("${app.folders}")
	private String FOLDER;

	@Autowired
	private FileTypesService sFileTypes;

	@Autowired
	private ContributorsService sContributors;

	@Autowired
	public FilesService(Dao<Files> dao) {
		super(dao);
	}

	@Override
	public void getComplexEntity(Files e) {
		e.setFile_type(sFileTypes.selectOne(FileTypesTable.colId, Comparator.eq, e.getFile_type_id()));
		e.setLast_author(sContributors.selectOne(ContributorsTable.colId, Comparator.eq, e.getLast_author_id()));
	}

	public JsonNode getJson(Files e) throws IOException {
		File source = Paths.get(this.FOLDER, e.getServerPath()).toFile();

		ObjectMapper objectMapper = new ObjectMapper();

		return objectMapper.readTree(source);
	}

	public byte[] getBytes(Files e) throws IOException {
		File source = Paths.get(this.FOLDER, e.getServerPath()).toFile();
		FileInputStream fis = new FileInputStream(source);
		byte[] content = fis.readAllBytes();

		fis.close();

		return content;
	}

	public byte[] getZip(List<Files> entities, Boolean hierarchy) throws IOException {
		ZipFile zf = new ZipFile().Open();

		for (int i = 0; i < entities.size(); i++) {
			Files e = entities.get(i);
			File source = Paths.get(this.FOLDER, e.getServerPath()).toFile();

			zf.WriteFull(hierarchy ? e.getFullPath() : e.getName(), source);
		}

		zf.Close();

		return zf.toByteArray();
	}
}
