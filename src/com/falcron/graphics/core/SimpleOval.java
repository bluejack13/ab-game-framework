package com.falcron.graphics.core;

import java.awt.Color;
import java.awt.Graphics2D;

import com.falcron.graphics.scene.SimpleScene;
import com.falcron.math.Vector2D;
import com.falcron.sprite.Entity;

public class SimpleOval extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Color color;
	public ShapeMode mode;
	
	public SimpleOval(Vector2D location, Color color, int width, int height,
			ShapeMode mode, SimpleScene scene) {
		super(width, height, null, scene);
		this.location = location;
		this.color = color;
		this.width = width;
		this.height = height;
		this.mode = mode;
	}
	
	public SimpleOval(int width, int height, Color color, SimpleScene scene){
		this(new Vector2D(), color, width, height, ShapeMode.Fill, scene);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		super.render(g2d);
		g2d.setColor(color);
	}
	
	@Override
	public void transformedRender(Graphics2D g2d) {
		if(this.mode == ShapeMode.Fill){
			g2d.fillOval((int)location.x, (int)location.y, width, height);
		}else{
			g2d.drawOval((int)location.x, (int)location.y, width, height);
		}
	}
	
}
