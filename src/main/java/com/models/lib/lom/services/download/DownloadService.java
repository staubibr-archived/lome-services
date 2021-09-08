package com.models.lib.lom.services.download;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.models.lib.lom.components.Query;
import com.models.lib.lom.components.Query.Comparator;
import com.models.lib.lom.components.ZipFile;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.files.FilesService;

@Repository
public class DownloadService {

	static String FOLDER = "D:\\Development\\arslab-services-v2\\files\\";

    private final FilesService service;
	
	@Autowired
	public DownloadService(FilesService service) {
		this.service = service;
	}
    
    public byte[] select(Query query) throws IOException {
    	return ZipEntities(service.select(query, false));
    }

    public byte[] select(String col, Comparator comp, Object value) throws IOException {
    	return ZipEntities(service.select(col, comp, value, false));
    }
    
    public byte[] selectOne(Query query) throws IOException {
    	return ZipEntities(service.select(query, false));
    }

	public byte[] selectOne(String col, Comparator comp, Object value) throws IOException {
    	return ZipEntities(service.select(col, comp, value));
	}
    
    private byte[] ZipEntities(List<Files> entities) throws IOException {
        ZipFile zf = new ZipFile().Open();
        
        for (int i = 0; i < entities.size(); i++) {
        	Files e = entities.get(i);
        	String name = e.getId().toString() + "_" + e.getName();
        	File source = Paths.get(FOLDER, name).toFile();
        	
    		zf.WriteFull(e.getName(), source);
        }
       
        zf.Close();
        
        return zf.toByteArray();
    }
}
