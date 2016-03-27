package com.falcron.util;

import java.awt.Color;
import java.awt.Graphics2D;

import com.falcron.core.Renderable;
import com.falcron.math.Vector2D;

public class Debug implements Renderable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Debug instance;
	public Color color;
	public Vector2D target;
	public int width, height;
	
	private Debug() {
		color = Color.red;
		target = new Vector2D();
		width = height = 20;
	}
	
	public static Debug getInstance(){
		if(instance==null){
			instance = new Debug();
		}
		return instance;
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillOval((int)target.x, (int)target.y, width, height);
	}

}
