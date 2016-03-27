package com.falcron.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Image {
	public static int[] getData(BufferedImage image){
		return ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	}
}
