package it.unipr.informatica.design_patterns.structural.proxy.file;

public abstract class FileHandler {
	protected String fileName_;
	
	protected FileHandler(String fileName) {
		fileName_ = fileName;
	}
	
	public String getFileName() {
		return fileName_;
	}
	
	public abstract String getContent();
	public abstract String getLine(int requestedLine);
}
