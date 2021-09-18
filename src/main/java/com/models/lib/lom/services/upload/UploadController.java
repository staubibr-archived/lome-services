package com.models.lib.lom.services.upload;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.models.lib.lom.components.Message;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.files.FilesService;

@RestController
public class UploadController {

	private final UploadService uService ;

    @Autowired
	public UploadController(UploadService uService, FilesService fService) {
		this.uService = uService;
	}

	@PostMapping(path = "api/upload/files", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<Long> uploadFile(@RequestPart("files") List<MultipartFile> files, @RequestPart String db_files) throws IOException{
		List<Files> l_db_files = this.uService.parse(db_files);
		
    	// TODO: This needs to be done at the same time files are uploaded.
		String message = this.uService.validate(files, l_db_files);
		
		if (message != null) throw new IllegalArgumentException(message) ;
		
		return this.uService.upload(files, l_db_files);
	}
}
