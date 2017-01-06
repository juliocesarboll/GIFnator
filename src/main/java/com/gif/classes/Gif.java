package com.gif.classes;

import java.util.Arrays;

public class Gif {
	private final double kb = 1000.0;
	private String name;
	private final String extension = ".gif";
	private byte[] gif;
	private double kbytes;

	public double getKb() {
		return kb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getGif() {
		return gif;
	}

	public void setGif(byte[] gif) {
		this.gif = gif;
	}

	public double getKbytes() {
		return kbytes;
	}

	public void setKbytes(double kbytes) {
		this.kbytes = kbytes;
	}

	public String getExtension() {
		return extension;
	}

	@Override
	public String toString() {
		return "Gif [name=" + name 
				+ ", extension=" + extension 
				+ ", gif=" + Arrays.toString(gif) 
				+ ", kbytes=" + kbytes
				+ "]";
	}
}
