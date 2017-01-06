package com.dropbox.classes;

public class Folder {
	private String folderName;
	private String folderLink;

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFolderLink() {
		return folderLink;
	}

	public void setFolderLink(String folderLink) {
		this.folderLink = folderLink;
	}

	public Folder(String folderName) {
		this.folderName = folderName;
		this.folderLink = shareFolder();
	}
	
	@Override
	public String toString() {
		return "Folder [folderName=" + folderName + ", folderLink=" + folderLink + "]";
	}

	public String shareFolder() {

		return "";
	}

	public boolean unshareFolder() {

		return false;
	}
}