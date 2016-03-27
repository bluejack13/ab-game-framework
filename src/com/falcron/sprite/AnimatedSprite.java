package com.falcron.sprite;

import com.falcron.core.ImageResource;
import com.falcron.graphics.scene.SimpleScene;

public class AnimatedSprite extends Sprite{

	private Animator animator;
	private Spritesheet sheet;
	public int row, col;
	public ImageResource imageresource;
	
	public AnimatedSprite(int width, int height, int row, int col, ImageResource irsc,
			SimpleScene scene) {
		super(width, height, irsc.getImage(), scene);
		this.imageresource = irsc;
		this.sheet = new Spritesheet(irsc.getImage(), row, col);
		this.animator = new Animator(sheet);
		this.row = row;
		this.col = col;
	}
	
	public Animator getAnimator(){return this.animator;}

	@Override
	public void update(float delta) {
		super.update(delta);
		this.image = this.animator.update();
	}
	
	
	private static final long serialVersionUID = 1L;

}
