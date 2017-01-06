package com.dropbox.classes;

public class File extends Folder {
	private String fileName;
	private String fileLink;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public File(String folderName, String fileName) {
		super(folderName);
		this.fileName = fileName;
		this.fileLink = shareFile();
	}
	
	@Override
	public String toString() {
		return "File [fileName=" + fileName + ", fileLink=" + fileLink + "]";
	}

	public String shareFile() {

		return "";
	}

	public boolean unshareFile() {

		return false;
	}
}