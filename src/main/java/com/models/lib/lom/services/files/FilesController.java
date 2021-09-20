package com.models.lib.lom.services.files;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.models.lib.lom.components.FilesResponse;
import com.models.lib.lom.components.services.Query;
import com.models.lib.lom.components.services.Query.Comparator;

@RestController
public class FilesController {

    private final FilesService fService;
    private final UploadService uService;	
    
    @Autowired
    public FilesController(FilesService fService, UploadService uService) {
        this.fService = fService;
        this.uService = uService;
    }
    
    @PostMapping("/api/files")
    public List<Object> create(@RequestBody List<Files> entities) {
    	return fService.create(entities);
    }

	@PostMapping(path = "api/files/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<Long> uploadFile(@RequestPart("files") List<MultipartFile> files, @RequestPart String db_files) throws IOException{
		List<Files> l_db_files = this.uService.parse(db_files);
		
		String message = this.uService.validate(files, l_db_files);
		
		if (message != null) throw new IllegalArgumentException(message) ;
		
		return this.uService.upload(files, l_db_files);
	}
    
    @GetMapping("/api/files/download/{id}")
    public ResponseEntity<byte[]> get(@PathVariable(value = "id") Long id,
			  						  @RequestParam(value = "name", defaultValue="files.zip") String name,
			  						  @RequestParam(value = "hierarchy", defaultValue="false") Boolean hierarchy) throws IOException {

    	List<Files> files = this.fService.select(FilesTable.colId, Comparator.in, id.toString(), false);

    	return FilesResponse.build(name, this.fService.zip(files, hierarchy));
    }

    @GetMapping("/api/files/download")
    public ResponseEntity<byte[]> list(@RequestParam(value = "ids", required = true) List<String> ids,
    								   @RequestParam(value = "name", defaultValue="files.zip") String name,
 			  						  @RequestParam(value = "hierarchy", defaultValue="false") Boolean hierarchy) throws IOException {

    	List<Files> files = this.fService.select(FilesTable.colId, Comparator.in, ids, false);
    	
    	return FilesResponse.build(name, this.fService.zip(files, hierarchy));
    }
    
    @GetMapping("/api/files/{id}")
    public Files get(@PathVariable(value = "id") Long id,
			  		 @RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	
    	return fService.selectOne(FilesTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/files")
    public List<Files> list(@RequestParam(value = "ids", required = false) List<String> ids,
   						  	@RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
                
        return fService.select(FilesTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/files")
    public List<Object> update(@RequestBody List<Files> entities) {
    	return fService.update(entities);
    }

    @DeleteMapping("/api/files")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return fService.delete(filesIds);
    }
}