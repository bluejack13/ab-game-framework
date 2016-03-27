package com.falcron.graphics.scene;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import com.falcron.core.Renderable;

public class SceneManager implements Renderable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Vector<Renderable>> layers;
	
	public SceneManager() {
		this.layers = new HashMap<String, Vector<Renderable>>();
	}
	
	public void addLayer(String layer){
		this.layers.put(layer, new Vector<Renderable>());
	}
	
	public void removeLayer(String layer){
		if(this.layers.containsKey(layer)){
			this.layers.remove(layer);
		}
	}
	
	public void addRenderable(String layer, Renderable renderable){
		if(this.layers.containsKey(layer)){
			this.layers.get(layer).add(renderable);
		}
	}

	@Override
	public void update(float delta) {
		synchronized (layers) {
			for(Entry<String, Vector<Renderable>> entry : layers.entrySet()){
				for(Renderable r : entry.getValue()){
					r.update(delta);
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		synchronized (layers) {
			for(Entry<String, Vector<Renderable>> entry : layers.entrySet()){
				for(Renderable r : entry.getValue()){
					r.render(g2d);
				}
			}
		}
	}
}
