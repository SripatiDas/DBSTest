package com.dbs.test.bean;

public class FileDetailResp {
	private String name;
	private String filePath;
	private boolean isRead;
	private boolean isWrite;
	private boolean isExecute;
	private String lastModified;
	private String createdOn;
	private String owner;
	private boolean isRegularFile;
	private boolean isSymbolicLink;
	private String lastAccessTime;
	
	public String getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public boolean isRegularFile() {
		return isRegularFile;
	}
	public void setRegularFile(boolean isRegularFile) {
		this.isRegularFile = isRegularFile;
	}
	public boolean isSymbolicLink() {
		return isSymbolicLink;
	}
	public void setSymbolicLink(boolean isSymbolicLink) {
		this.isSymbolicLink = isSymbolicLink;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public boolean isWrite() {
		return isWrite;
	}
	public void setWrite(boolean isWrite) {
		this.isWrite = isWrite;
	}
	public boolean isExecute() {
		return isExecute;
	}
	public void setExecute(boolean isExecute) {
		this.isExecute = isExecute;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	

}
