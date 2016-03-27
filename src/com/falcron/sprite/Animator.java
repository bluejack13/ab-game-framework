package com.falcron.sprite;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;


public class Animator{
	
	private Spritesheet sheet;
	private HashMap<String, Animation> animations = new HashMap<String, Animation>();
	private Animation idle;
	private String idleanimation;
	
	public Animator(Spritesheet sheet) {
		this.sheet = sheet;
	}
	
	public HashMap<String, Animation> getAnimations(){
		return this.animations;
	}
	
	public String getIdleAnimation(){return this.idleanimation;}
	
	public void setIdleAnimation(String animationName){
		if(animations.containsKey(animationName)){
			this.idleanimation = animationName;
			if(idle == animations.get(animationName)){
				if(isEndOfAnimation()) sheet.reset(); 
			}else{
				idle = animations.get(animationName);
				sheet.reset();
			}
		}
	}
	
	public Animation getAnimation(String name){
		if(animations.containsKey(name)){
			return animations.get(name);
		}
		return  null;
	}
	
	public String getAnimationKey(Animation anim){
		if(animations.containsValue(anim)){
			for(Entry<String, Animation> en : animations.entrySet()){
				if(en.getValue() == anim){
					return en.getKey();
				}
			}
		}
		return  null;
	}
	
	
	public BufferedImage update(){
		if(animations.size() > 0 && idle != null){
				BufferedImage tmp = idle.play(this);
				if(tmp==null){
					idle = idle.transition;
					tmp = idle.play(this);
					sheet.reset();
				}
				return tmp;
		}else{
			return null;
		}
	}
	
	public void addAnimation(String animationName, Animation animation){
		this.animations.put(animationName, animation);
	}
	
	public BufferedImage playAnimation(String animationName, int framerate, boolean loop){
		return sheet.playForward(animations.get(animationName).row, framerate, loop);
	}
	
	public BufferedImage playAnimation(int row, int col, int framerate, boolean loop){
		return sheet.playForward(row, col, framerate,  loop);
	}
	
	public boolean isEndOfAnimation(){return sheet.endOfAnimation;}
}
