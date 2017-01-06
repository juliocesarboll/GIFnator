package com.gif.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gif.classes.Gif;
import com.gif.classes.GifConfig;
import com.gif.classes.MakeGif;

import junit.framework.TestCase;

public class Sized extends TestCase {
	Gif gif = new Gif();
	MakeGif makeGif = new MakeGif();
	GifConfig config = new GifConfig();
	List<File> imgList = new ArrayList<File>();
	File dirFile = new File("./src/test/resources/Imagens");
	String name = "px";
	
	public void setUp() {
		name = "px";

		if (!dirFile.isDirectory()) {
			System.out.println("Não é um diretório");
			System.exit(0);
		} else {
			imgList = Arrays.asList(dirFile.listFiles());
		}

		config.setHeight(300);
		config.setWidth(300);

		int tempo = 2;
		config.setDelay((tempo / imgList.size()) * 100);
		config.setLoop(true);
	}

	public void testSized() throws IOException {
		if (name.contains(".") || name.contains(",")) {
			System.out.println("Nome inválido.");
			System.exit(0);
		} else {
			gif.setName(name);
		}
		
		FileOutputStream fos = new FileOutputStream("./src/test/resources/Gifs/" + gif.getName() + gif.getExtension());
		gif.setGif(makeGif.sized(imgList, config));
		gif.setKbytes(gif.getGif().length/gif.getKb());
//		System.out.println(gif.toString());
		
		fos.write(gif.getGif());
		fos.close();
	}
}
