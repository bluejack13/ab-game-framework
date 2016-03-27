package com.ab2ds.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import com.falcron.core.Renderable;
import com.falcron.math.Vector2D;

public class GameObject implements Renderable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector2D position;
	public int width, height;
	public Color color;
	
	
	public GameObject(Vector2D position, int width, int height, Color color) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.fillRect((int)position.x, (int)position.y, width, height);
	}

}
