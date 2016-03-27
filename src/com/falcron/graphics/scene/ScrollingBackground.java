package com.falcron.graphics.scene;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.falcron.core.Renderable;
import com.falcron.math.Vector2D;

public final class ScrollingBackground implements Renderable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	private BufferedImage image;
	private Vector2D location;
	private SimpleScene scene;
	private int count, mode, width, height;
	public float speed;
	
	public ScrollingBackground(BufferedImage image, float speed, int mode, SimpleScene scene) {
		this(image, image.getWidth(), image.getHeight(), speed, mode, scene);
	}
	
	public ScrollingBackground(BufferedImage image, int w, int h, float speed, int mode, SimpleScene scene){
		this.image = image;
		this.scene = scene;
		this.speed = speed;
		this.width = w;
		this.height = h;
		if(mode!=UP && mode!=DOWN && mode!=LEFT && mode!=RIGHT) this.mode = LEFT;
		else this.mode = mode;
		this.mode = mode;
		this.location = new Vector2D();
	}

	@Override
	public void update(float delta) {
		switch (mode) {
			case LEFT:{
				location.x -= speed;
				count += speed;
				if(count >= scene.getWidth()){
					location.x = 0;
					count=0;
				}
				break;
			}
			case RIGHT:{
				location.x += speed;
				count += speed;
				if(count >= scene.getWidth()){
					location.x = 0;
					count=0;
				}
				break;
			}
		}
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(image, (int)location.x, (int)location.y, width, height, null);

		switch (mode) {
			case LEFT:{
				g2d.drawImage(image, (int)(scene.getWidth()-count), (int)location.y, width, height, null);
				break;
			}
			case RIGHT:{
				g2d.drawImage(image, (int)-scene.getWidth()+count, (int)location.y, width, height, null);
				break;
			}
		}
	}
	
}
