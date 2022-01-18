package com.lom.components;

import java.io.IOException;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class FilesResponse {

	public ResponseEntity<byte[]> response = null;
	
	public FilesResponse(String name, byte[] content) throws IOException {
    	ContentDisposition disposition = ContentDisposition.attachment().filename(name).build();
    	
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, disposition.toString());
		
        this.response = ResponseEntity.ok().headers(httpHeaders).body(content);
	}
	
	public static ResponseEntity<byte[]> build(String name, byte[] content) throws IOException {
		FilesResponse fr = new FilesResponse(name, content);
		
		return fr.response;
	}
}
