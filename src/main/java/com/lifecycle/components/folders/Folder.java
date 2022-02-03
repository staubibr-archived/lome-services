package com.lifecycle.components.folders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

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

	public void copy(InputStream f, String file_name) throws IOException {
		Path copy_path = Paths.get(folder.toString(), file_name);

		java.nio.file.Files.copy(f, copy_path, StandardCopyOption.REPLACE_EXISTING);
		
		f.close();
	}

	public void copy(MultipartFile f) throws IOException {
		this.copy(f.getInputStream(), f.getOriginalFilename());
	}

	public void copy(File f) throws IOException {
		this.copy(new FileInputStream(f), f.getName());
	}

	public void copy(MultipartFile f, String file_name) throws IOException {
		this.copy(f.getInputStream(), file_name);
	}

	public void copy(File f, String file_name) throws IOException {
		this.copy(new FileInputStream(f), file_name);
	}
	
	public Folder makeFolder(String name) throws IOException {
		Path path = this.path(name);
		
		path.toFile().mkdirs();
		
		return new Folder(path);
	}
	
	public void delete() throws IOException {
		File directory = new File(folder.toString());
		
		if (!directory.exists()) throw new IOException("Cannot delete folder " + folder.toString() + ", it does not exist.");
		
		FileSystemUtils.deleteRecursively(directory);

		directory.delete();
	}
	
	public Path path(String... file_name) {
		return Paths.get(folder.toString(), file_name);
	}
	
	public List<File> files() {
		File folder = new File(this.folder.toString());
		
		return new ArrayList<>(Arrays.asList(folder.listFiles()));
	}
	
	public List<File> files(String ... file_names) {
		File folder = path(file_names).toFile();

		return new ArrayList<>(Arrays.asList(folder.listFiles()));
	}
	
	public File file(String file_name) {
		return new File(path(file_name).toString());
	}
	
	public File file(String... file_name) {		
		return new File(path(file_name).toString());
	}
}
