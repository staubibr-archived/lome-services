package com.lom.components;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class Scratch {
	

	private String root;

	public UUID uuid;
	public Path folder;
	
	public Scratch(String root) throws IOException {
		root = root;
		File directory;
				
		do {
			uuid = UUID.randomUUID();
			folder = Paths.get(root, uuid.toString());
			directory = new File(folder.toString());
		} while (directory.exists());
		
		// if (directory.exists()) throw new IOException("Cannot create scratch folder " + uuid.toString() + ", it already exists.");
		
		directory.mkdirs();
	}

    
	public Path Copy(MultipartFile f) throws IOException {
		Path copy_path = Paths.get(folder.toString(), f.getOriginalFilename());
		File copy_file = new File(copy_path.toString());

		if (copy_file.exists()) throw new IOException("Cannot copy file " + copy_file.toString() + ", it already exists.");
		
		java.nio.file.Files.copy(f.getInputStream(), copy_path, StandardCopyOption.REPLACE_EXISTING);
		
		return copy_path;
	}
    
	public void Copy(List<MultipartFile> files) throws IOException {
		for (MultipartFile f : files) Copy(f);
	}
	
	public void Delete() throws IOException {
		File directory = new File(folder.toString());
		
		if (!directory.exists()) throw new IOException("Cannot delete scratch folder " + folder.toString() + ", it does not exist.");
		
		FileSystemUtils.deleteRecursively(directory);

		directory.delete();
	}
	
	public Path Path(String file_name) {
		return Paths.get(folder.toString(), file_name);
	}
	
	public File Get(String file_name) {
		return new File(Path(file_name).toString());
	}
	
	public List<File> Get(String ... file_names) {
	    return Arrays.asList(file_names).stream().map(f -> Get(f)).collect(Collectors.toList());
	}
	
	
}
