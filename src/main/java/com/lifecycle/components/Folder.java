package com.lifecycle.components;

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

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.components.ZipFile;

public class Folder {

	protected String path;
	public Path folder;
	
	public Folder(String path, Boolean create) throws IOException {
		this.path = path;
		this.folder = Paths.get(this.path);
		
		File directory = new File(folder.toString());
		
		if (directory.exists()) return;
		
		if (create) directory.mkdirs();
		
		else throw new IOException("Folder " + path + " does not exist.");
	}
	
	public Folder(String path) throws IOException {
		this(path, false);
	}
	
	public Folder(Path path, Boolean create) throws IOException {
		this(path.toString(), create);
	}
	
	public Folder(Path path) throws IOException {
		this(path, false);
	}
	
	public Folder(String first, String ...more) throws IOException {		
		this(Paths.get(first, more));
	}
	
	public Folder(String first, UUID uuid) throws IOException {		
		this(Paths.get(first, uuid.toString()));
	}
	
	public void Copy(MultipartFile f, String file_name, Boolean overwrite) throws IOException {
		Path copy_path = Paths.get(folder.toString(), file_name);
		File copy_file = new File(copy_path.toString());

		if (!overwrite && copy_file.exists()) throw new IOException("Cannot copy file " + copy_file.toString() + ", it already exists.");
		
		java.nio.file.Files.copy(f.getInputStream(), copy_path, StandardCopyOption.REPLACE_EXISTING);
	}

	public void Copy(MultipartFile f, Boolean overwrite) throws IOException {		
		this.Copy(f, f.getOriginalFilename(), overwrite);
	}

	public void Copy(List<MultipartFile> files, Boolean overwrite) throws IOException {
		for (MultipartFile f : files) this.Copy(f, overwrite);
	}
	
	public void Delete() throws IOException {
		File directory = new File(folder.toString());
		
		if (!directory.exists()) throw new IOException("Cannot delete folder " + folder.toString() + ", it does not exist.");
		
		FileSystemUtils.deleteRecursively(directory);

		directory.delete();
	}
	
	public Path Path(String file_name) {
		return Paths.get(folder.toString(), file_name);
	}
	
	public File Get(String file_name) {
		return new File(Path(file_name).toString());
	}
	
	public List<File> Get() {
		File folder = new File(this.folder.toString());
		
		return new ArrayList<>(Arrays.asList(folder.listFiles()));
	}
	
	public List<File> Get(String ... file_names) {
	    return Arrays.asList(file_names).stream().map(f -> Get(f)).collect(Collectors.toList());
	}
	
	public ZipFile Zip(String ... file_names) throws IOException {
    	return new ZipFile(Get(file_names));
	}
}
