package com.falcron.sprite;

import java.awt.Graphics2D;
import java.util.Vector;

import com.falcron.graphics.scene.SimpleScene;

public class Node extends Entity{

	private Vector<Entity> childs = new Vector<Entity>();
	
	public Node(SimpleScene scene) {
		super(0, 0, null, scene);
	}
	
	public void addChild(Entity entity){
		entity.parent = this;
		childs.add(entity);
	}
	
	@Override
	public void update(float delta) {
		for(Entity e : childs){
			e.update(delta);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		for(Entity e : childs){
			e.render(g2d);
		}
	}

}
