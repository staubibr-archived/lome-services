package com.models.lib.lom.services.files;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.models.lib.lom.components.Dao;
import com.models.lib.lom.components.Query.Comparator;
import com.models.lib.lom.components.Service;
import com.models.lib.lom.services.contributors.ContributorsService;
import com.models.lib.lom.services.contributors.ContributorsTable;
import com.models.lib.lom.services.file_types.FileTypesService;
import com.models.lib.lom.services.file_types.FileTypesTable;

@Repository
public class FilesService extends Service<Files> {

	static final String FOLDER = "D:\\Development\\arslab-services-v2\\files\\";

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
	
	public List<Files> parse(String db_files) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
	    CollectionType clss = mapper.getTypeFactory().constructCollectionType(List.class, Files.class);

	    return mapper.readValue(db_files, clss);
	}
	
	public String validate(List<MultipartFile> files, List<Files> db_files) {
		if (files.isEmpty()) return "Empty list of files provided.";

		if (db_files.isEmpty()) return "Empty list of database files provided.";

		if (db_files.size() != files.size()) return "Length mismatch between the ids and files provided.";

		for (int i = 0; i < files.size(); i++) { 
			if (!files.get(i).getOriginalFilename().equals(db_files.get(i).getName())) {
				return "File name mismatch between between files provided.";
			}
		}
		
		return null;
	}
	
    private void copyFile(MultipartFile f, BigInteger id) throws IOException {
		String name = FOLDER + id.toString() + "_" + f.getOriginalFilename();
		
		java.nio.file.Files.copy(f.getInputStream(), Paths.get(name), StandardCopyOption.REPLACE_EXISTING);
    }
    
    public List<Long> upload(List<MultipartFile> files, List<Files> db_files) throws IOException {
		List<BigInteger> ids = this.create(db_files).stream().map(id -> (BigInteger)id).collect(Collectors.toList());

		for (int i = 0; i < files.size(); i++) {
			this.copyFile(files.get(i), ids.get(i));
		}
		
		return ids.stream().map(x -> x.longValue()).collect(Collectors.toList());
    }
}
