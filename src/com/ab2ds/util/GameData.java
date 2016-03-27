package com.ab2ds.util;

import java.io.Serializable;
import java.util.Vector;

import com.falcron.core.Renderable;

public class GameData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector<Renderable> renderables;
	
	public GameData(Vector<Renderable> renderables) {
		super();
		this.renderables = renderables;
	}
	
	
}
