package com.falcron.sprite;

import java.awt.image.BufferedImage;


public final class Spritesheet {
	
	private BufferedImage image;
	private int row, col, width, height, frames;
	public int count;
	public boolean endOfAnimation = false;
	
	public void reset(){
		frames=count=0;
		endOfAnimation=false;
	}
	
	public Spritesheet(BufferedImage image, int row, int col) {
		this.image = image;
		this.row = row;
		this.col = col;
		this.width = image.getWidth() / col;
		this.height = image.getHeight() / row;
	}
	
	public BufferedImage getImage(int row, int col){
		return image.getSubimage(col * width, row * height, width, height);
	}
	
	public BufferedImage playForward(int row, int col, int framerate, boolean loop){
		frames++;
		if(frames == framerate){
			frames = 0;
			count++;
			if(count == col) {
				if(!loop){
					count = col - 1;
				}else{
					count = 0;
				}
				endOfAnimation = true;
			}
		}
		if(count==col && !loop){ count=col-1;}
		return getImage(row, count);
	}
	public BufferedImage playForward(int row, int framerate, boolean loop){
		return playForward(row, this.col, framerate, loop);
	}
}
