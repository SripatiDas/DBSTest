package com.dbs.test.bean;

public class FileDirResponse {
	private String fileDirPath;
	private String fileName;
	private long size;
	private boolean isDirectory;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public FileDirResponse(String fileDirPath, long size, boolean isDirectory) {
		super();
		this.fileDirPath = fileDirPath;
		this.size = size;
		this.isDirectory = isDirectory;
	}
	public String getFileDirPath() {
		return fileDirPath;
	}
	public void setFileDirPath(String fileDirPath) {
		this.fileDirPath = fileDirPath;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isDirectory() {
		return isDirectory;
	}
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	

}
