package com.falcron.core;

import java.awt.image.BufferedImage;

public final class ImageResource {
	
	public String resourcename, path;
	
	public ImageResource(String resourcename, String path) {
		this.resourcename = resourcename;
		this.path = path;
		GameResource.getInstance().addImageResource(resourcename, path);
	}
	
	public BufferedImage getImage(){
		return GameResource.getInstance().getImageResource(resourcename);
	}
}
