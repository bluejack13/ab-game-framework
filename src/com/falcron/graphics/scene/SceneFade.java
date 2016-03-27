package com.falcron.graphics.scene;

import java.awt.Color;
import java.awt.Graphics2D;

import com.falcron.math.Vector2D;

public class SceneFade extends SceneTransition{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DOWN = 0;
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	private float speed;
	public Color color;
	public Vector2D location;
	public int mode;
	
	public SceneFade(SimpleScene scene, float speed, int mode){
		super(scene);
		this.play = false;
		this.scene = scene;
		this.speed = speed;
		this.color = Color.black;
		this.location = new Vector2D();
		this.mode = mode;
		setTransitionMode(this.mode);
	}
	
	public void setTransitionMode(int mode){
		switch(mode){
		case UP:{
			location.y = scene.getHeight();
			break;
		}
		case DOWN:{
			location.y = -scene.getHeight();
			break;
		}
		case LEFT:{
			location.x = scene.getWidth();
			break;
		}
		case RIGHT:{
			location.x = -scene.getWidth();
			break;
		}
		}
	}

	public void play(){
		this.play = true;
	}
	
	@Override
	public void update(float delta) {
		if(play){
			switch(mode){
			case UP:{
				location.y -= speed;
				if(location.y <= 0){
					done=true;
				}
				break;
			}
			case DOWN:{
				location.y += speed;
				if(location.y >= 0){
					done=true;
				}
				break;
			}
			case LEFT:{
				location.x -= speed;
				if(location.x <= 0){
					done=true;
				}
				break;
			}
			case RIGHT:{
				location.x += speed;
				if(location.x >= 0){
					done=true;
				}
				break;
			}
			}
			
			if(done){
				play=false;
			}
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		if(play){
			g2d.setColor(color);
			g2d.fillRect((int)location.x, (int)location.y, scene.getWidth(), scene.getHeight());
		}
	}
}
