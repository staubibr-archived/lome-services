package com.models.lib.lom.services.download;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.models.lib.lom.components.Query.Comparator;
import com.models.lib.lom.services.files.Files;
import com.models.lib.lom.services.files.FilesTable;

@RestController
public class DownloadController {

    private final DownloadService service;

    @Autowired
    public DownloadController(DownloadService service) {
        this.service = service;
    }
    
    @GetMapping("/api/download/files/{id}")
    public ResponseEntity<byte[]> get(@PathVariable(value = "id") Long id,
    								  @RequestParam(value = "name", defaultValue="files.zip") String name) throws IOException {
    	
    	byte[] content = service.select(FilesTable.colId, Comparator.eq, id.toString());
    	
    	return ByteResponse(name, content);
    }

    @GetMapping("/api/download/files")
    public ResponseEntity<byte[]> list(@RequestParam(value = "ids", required = true) List<String> ids,
    								   @RequestParam(value = "name", defaultValue="files.zip") String name) throws IOException {

    	byte[] content = service.select(FilesTable.colId, Comparator.in, ids);
    	
    	return ByteResponse(name, content);
    }
    
    private ResponseEntity<byte[]> ByteResponse(String name, byte[] content) {
    	ContentDisposition disposition = ContentDisposition.attachment().filename(name).build();
    	
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
        
        return ResponseEntity.ok().headers(httpHeaders).body(content);
    }
}
