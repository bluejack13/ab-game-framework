package com.falcron.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.falcron.graphics.scene.SimpleScene;

public abstract class SimpleGame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//To store the SimpleScene canvas UI
	public JPanel scenepanel = new JPanel();
	
	//Current scene name
	protected String currentScene;
	
	//Stores all scenes in hashmap
	protected HashMap<String, Class<?extends SimpleScene>> scenes = new HashMap<String, Class<? extends SimpleScene>>();
	
	public SimpleGame(int width, int height) {
		setTitle("Simple Game");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.black);
		scenepanel.setBackground(Color.black);
		scenepanel.setLayout(new BorderLayout());
		add(scenepanel);
		this.initResource();
		this.initScene();
	}
	
	public SimpleGame(){
		this(720, 480);
	}
	
	public abstract void initScene();
	public abstract void initResource();
	
	public void addScene(String name, Class<? extends SimpleScene> c){
		this.scenes.put(name, c);
	}
	
	public void deleteScene(String name){
		this.scenes.remove(name);
	}
	
	private SimpleScene getScene(String name){
		try {
			Class<? extends SimpleScene> c = (Class<? extends SimpleScene>) Class.forName(scenes.get(name).getName());
			Constructor<?> cn = c.getConstructor(SimpleGame.class);
			SimpleScene s = (SimpleScene) cn.newInstance(new Object[]{this});
			
			return s;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	public SimpleScene loadScene(String name){
		if(this.scenes.containsKey(name)){
			scenepanel.removeAll();
			try {
				currentScene = name;
				SimpleScene s = getScene(name);
				scenepanel.add(s);
				s.start();
				return s;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("Scene '"+name+"' is not defined");
		}
		return null;
	}
	
	
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		setLocationRelativeTo(null);
	}
	
	public void setCursor(String imagename){
		setCursor(getToolkit().createCustomCursor(GameResource.getInstance().getImageResource(imagename), new Point(), null));
	}
	
	public void setFullscreen(boolean b){
		if(b){
			dispose();
			setUndecorated(true);
			setExtendedState(MAXIMIZED_BOTH);
			setVisible(true);
		}else{
			dispose();
			setUndecorated(false);
			setExtendedState(NORMAL);
			setVisible(true);
		}
	}
	
	public static void main(String[]args){
		
	}
}
