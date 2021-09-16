package com.models.lib.lom.services.upload;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	
	private final UploadService service ;
	
	public UploadController(UploadService service) {
		this.service=service;
	}

	@PostMapping("api/upload/files")
	public ResponseEntity<String> uploadFile(@RequestParam("files") List<MultipartFile> files, @RequestParam("ids") List<String> ids){
		
		boolean successfullUpload=false;
		
		if(files.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file(s) to upload");
		}
		for(int i=0;i<files.size();i++) {
			successfullUpload= service.uploadFile(files.get(i), ids.get(i));
		}
		if(successfullUpload) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Files successfully uploaded");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There was some problem with the request");
	}
}
