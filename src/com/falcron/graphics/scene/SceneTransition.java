package com.falcron.graphics.scene;

import java.awt.Graphics2D;

import com.falcron.core.Renderable;

public abstract class SceneTransition implements Renderable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected SimpleScene scene;
	public boolean done, play;
	
	public SceneTransition(SimpleScene scene){
		this.scene = scene;
	}
	
	public void play(){
		this.play = true;
	}

	@Override
	public void render(Graphics2D g2d) {
		if(!play) return;
	}
	
	@Override
	public void update(float delta) {
		if(!play) return;
	}
}
