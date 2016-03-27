package com.falcron.core;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.falcron.graphics.scene.SimpleScene;
import com.falcron.input.mouse.MouseInteractable;
import com.falcron.sprite.Entity;

public abstract class SimpleScript implements Renderable, MouseInteractable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Renderable renderable;
	public Entity entity;
	public SimpleScene scene;
	
	public SimpleScript(Renderable renderable) {
		this.renderable = renderable;
		if(this.renderable instanceof Entity){
			this.entity = (Entity) this.renderable;
			this.scene = this.entity.scene;
		}
	}

	@Override
	public void onClick(MouseEvent event) {
		
	}
	
	@Override
	public void onDoubleClick(MouseEvent event) {
		
	}
	
	@Override
	public Rectangle getBounds() {
		if(this.entity != null){
			return this.entity.getBounds();
		}
		return null;
	}
	
	@Override
	public void onEnter(MouseEvent event) {
		
	}
	
	@Override
	public void onExit(MouseEvent event) {
		
	}
	
	@Override
	public void onPress(MouseEvent event) {
		
	}
	
	@Override
	public void onRelease(MouseEvent event) {
		
	}
}
