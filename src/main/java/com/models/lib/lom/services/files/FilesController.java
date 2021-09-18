package com.models.lib.lom.services.files;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.models.lib.lom.components.Query;

@RestController
public class FilesController {

    private final FilesService service;

    @Autowired
    public FilesController(FilesService service) {
        this.service = service;
    }
    
    @PostMapping("/api/files")
    public List<Object> create(@RequestBody List<Files> entities) {
    	return service.create(entities);
    }

	@PostMapping(path = "api/files/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public List<Long> uploadFile(@RequestPart("files") List<MultipartFile> files, @RequestPart String db_files) throws IOException{
		List<Files> l_db_files = this.service.parse(db_files);
		
		String message = this.service.validate(files, l_db_files);
		
		if (message != null) throw new IllegalArgumentException(message) ;
		
		return this.service.upload(files, l_db_files);
	}
    
    @GetMapping("/api/files/{id}")
    public Files get(@PathVariable(value = "id") Long id,
			  		 @RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
    	
    	return service.selectOne(FilesTable.colId, Query.Comparator.eq, id.toString(), complex);
    }

    @GetMapping("/api/files")
    public List<Files> list(@RequestParam(value = "ids", required = false) List<String> ids,
   						  	@RequestParam(value = "complex", defaultValue = "false") Boolean complex) {
                
        return service.select(FilesTable.colId, Query.Comparator.in, ids, complex);
    }

    @PutMapping("/api/files")
    public List<Object> update(@RequestBody List<Files> entities) {
    	return service.update(entities);
    }

    @DeleteMapping("/api/files")
    public List<Object> delete(@RequestBody List<Object> filesIds) {
    	return service.delete(filesIds);
    }
}