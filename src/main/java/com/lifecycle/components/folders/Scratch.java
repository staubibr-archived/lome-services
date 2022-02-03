package com.lifecycle.components.folders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class Scratch extends Folder {
	
	public UUID uuid;
	
	public Scratch(String path) throws IOException {
		super(path);
		
		File directory;
				
		do {
			this.uuid = UUID.randomUUID();
			this.folder = Paths.get(this.path, uuid.toString());
			directory = new File(folder.toString());
		} while (directory.exists());
				
		directory.mkdirs();
	}
}
