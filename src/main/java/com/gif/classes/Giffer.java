package com.gif.classes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

public abstract class Giffer {

	public static byte[] generateFromBuffImg(BufferedImage[] buffImgs, int delay, boolean loop)
			throws IIOException, IOException {

		ImageWriter gifWriter = getWriter();
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ImageOutputStream imgOutPutStr = ImageIO.createImageOutputStream(bytes);

		IIOMetadata metadata = getMetadata(gifWriter, delay, loop);
		gifWriter.setOutput(imgOutPutStr);
		gifWriter.prepareWriteSequence(null);

		for (BufferedImage img : buffImgs) {
			IIOImage temp = new IIOImage(img, null, metadata);
			gifWriter.writeToSequence(temp, null);
		}

		gifWriter.endWriteSequence();
		imgOutPutStr.close();
		gifWriter.dispose();
		return bytes.toByteArray();
	}

	private static ImageWriter getWriter() throws IIOException {
		Iterator<ImageWriter> itr = ImageIO.getImageWritersByFormatName("gif");
		if (itr.hasNext())
			return itr.next();

		throw new IIOException("GIF writer doesn't exist on this JVM!");
	}

	private static IIOMetadata getMetadata(ImageWriter writer, int delay, boolean loop) throws IIOInvalidTreeException {

		ImageTypeSpecifier imgType = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_ARGB);
		IIOMetadata metadata = writer.getDefaultImageMetadata(imgType, null);
		String natForm = metadata.getNativeMetadataFormatName();
		IIOMetadataNode nodeTree = (IIOMetadataNode) metadata.getAsTree(natForm);

		IIOMetadataNode graphNode = getNode("GraphicControlExtension", nodeTree);
		graphNode.setAttribute("delayTime", String.valueOf(delay));
		graphNode.setAttribute("disposalMethod", "none");
		graphNode.setAttribute("userInputFlag", "FALSE");

		if (loop)
			makeLoopy(nodeTree);

		metadata.setFromTree(natForm, nodeTree);

		return metadata;
	}

	private static void makeLoopy(IIOMetadataNode root) {
		IIOMetadataNode appExt = getNode("ApplicationExtensions", root);
		IIOMetadataNode appNode = getNode("ApplicationExtension", appExt);

		appNode.setAttribute("applicationID", "NETSCAPE");
		appNode.setAttribute("authenticationCode", "2.0");
		appNode.setUserObject(new byte[] { 0x1, (byte) (0 & 0xFF), (byte) ((0 >> 8) & 0xFF) });

		appExt.appendChild(appNode);
		root.appendChild(appExt);
	}

	private static IIOMetadataNode getNode(String nodeName, IIOMetadataNode root) {
		IIOMetadataNode node = null;

		for (int i = 0; i < root.getLength(); i++) {
			if (root.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0) {
				node = (IIOMetadataNode) root.item(i);
				return node;
			}
		}
		node = new IIOMetadataNode(nodeName);
		root.appendChild(node);

		return node;
	}
}