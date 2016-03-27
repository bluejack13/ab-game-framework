package com.falcron.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public final class GameResource {
	private static GameResource gameresource = new GameResource();
	private static HashMap<String, BufferedImage> imageresource = new HashMap<String, BufferedImage>();
	private static String imagepath = "img";
	private static String soundpath = "sound";
	
	private GameResource(){
	}
	
	public void setImageResourcePath(String path){
		GameResource.imagepath = path;
	}
	
	public void setSoundResourcePath(String path){
		GameResource.soundpath = path;
	}
	
	public BufferedImage addImageResource(String name, String imagename){
		try {
			BufferedImage bimg = ImageIO.read(new File(imagepath+"/"+imagename));
			imageresource.put(name, bimg);
			return bimg;
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage getImageResource(String name){
		if(imageresource.containsKey(name)){
			return imageresource.get(name);
		}else{
			return null;
		}
	}
	
	public static GameResource getInstance(){
		if(gameresource==null){
			gameresource = new GameResource();
		}
		return gameresource;
	}
}
