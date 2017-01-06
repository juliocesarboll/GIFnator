package com.gif.classes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class MakeGif {

	private BufferedImage reSize(BufferedImage img, int width, int height) {

		BufferedImage new_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = new_img.createGraphics();
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();

		return new_img;
	}

	public byte[] sized(List<File> imgsList, GifConfig config) throws IOException, NullPointerException {
		if(imgsList.size() == 0){
			System.out.println("Lista de arquivos nula!");
			throw new NullPointerException();
		}else{
		
		BufferedImage[] buffImgs = new BufferedImage[imgsList.size()];

		for (int i = 0; i < imgsList.size(); i++) {
			BufferedImage buffedImg = ImageIO.read(imgsList.get(i));
			buffedImg = reSize(buffedImg, config.getWidth(), config.getHeight());
			buffImgs[i] = buffedImg;
		}

		return Giffer.generateFromBuffImg(buffImgs, config.getDelay(), config.isLoop());
		}
	}
}
