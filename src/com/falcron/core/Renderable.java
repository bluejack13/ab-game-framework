package com.falcron.core;

import java.awt.Graphics2D;
import java.io.Serializable;

public interface Renderable extends Serializable{
	public void update(float delta);
	public void render(Graphics2D g2d);
}
