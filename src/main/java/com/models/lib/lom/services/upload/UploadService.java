package com.models.lib.lom.services.upload;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.models.lib.lom.services.files.FilesService;

@Service
public class UploadService {

	static final String FOLDER = "D:\\Development\\arslab-services-v2\\files\\";

    private final FilesService service;
    
    public UploadService(FilesService service) {
    	this.service=service;
    }
    
    public boolean uploadFile(MultipartFile f, String id) {
    	
    	boolean uploaded= false;
    	
    	try {
    		Files.copy(f.getInputStream(),Paths.get(FOLDER+id+"_"+f.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING );
    		uploaded=true;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return uploaded;
    }
}
