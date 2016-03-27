package com.falcron.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public final class Keyboard implements KeyListener{
	
	private boolean[] keys = new boolean[120];
	private HashMap<String, Integer> mappedKeys = new HashMap<String, Integer>();
	
	public boolean up, down, left, right, space;

	public void update(){
		//Basic keys
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
	}
	
	public boolean get(int keyCode){
		return keys[keyCode];
	}
	
	public boolean get(String keyName){
		if(mappedKeys.containsKey(keyName)){
			return get(mappedKeys.get(keyName));
		}else{
			return false;
		}
	}
	
	public void addCustomKey(String keyName, int keyCode){
		mappedKeys.put(keyName, keyCode);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

}
