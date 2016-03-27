package com.falcron.sprite;

import java.awt.image.BufferedImage;

public final class Animation {
	public int row, col, framerate;
	public boolean loop;
	public Animation transition;
	
	public Animation(int row, int col, int framerate, boolean loop){
		this.row = row;
		this.col = col;
		this.loop = loop;
		this.framerate = framerate;
	}
	
	public void setTransition(Animation anim){
		this.transition = anim;
	}
	
	public BufferedImage play(Animator animator){
		if(!loop && animator.isEndOfAnimation() && transition!=null){
			return null;
		}
		return animator.playAnimation(row, col, framerate, loop);
	}
}
