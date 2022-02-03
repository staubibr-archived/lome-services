package com.lifecycle.components.processes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.lifecycle.components.folders.Folder;

public abstract class Process {
	
	protected ProcessBuilder pb;
	
	protected Folder workspace = null;
	protected File tool = null; 
	
	public Process() {

	}
	
	private int execute(ProcessBuilder pb) throws IOException, InterruptedException {
		if (this.workspace != null) pb.directory(this.workspace.folder.toFile());
		
		pb.redirectError();
		
		java.lang.Process p = pb.start();
		int value = -1;
		
		while ((value = p.getInputStream().read()) != -1) System.out.print((char) value);

		int exit = p.waitFor();
		
		p.destroy();
		
		return exit;
	}
		
	protected int execute(List<String> command) throws IOException, InterruptedException {
		return this.execute(new ProcessBuilder(command));
	}

	
	protected int execute(String... command) throws IOException, InterruptedException {
		return this.execute(new ProcessBuilder(command));
	}
}
