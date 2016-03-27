package com.falcron.math;

import java.awt.Graphics2D;
import java.util.Vector;

import com.falcron.core.Renderable;
import com.falcron.graphics.scene.SimpleScene;
import com.falcron.sprite.Entity;

public final class Physics implements Renderable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Vector2D GRAVITY = new Vector2D(0, 50f);
	public static final Vector2D WINDLEFT = new Vector2D(-3f, 0);
	public static final Vector2D WINDRIGHT = new Vector2D(0, -3f);
	private SimpleScene scene;
	private Vector<PhysicsEntity> physicsentities = new Vector<PhysicsEntity>();
	
	public Physics(SimpleScene scene) {
		this.scene = scene;
	}
	
	public void addPhysicsEntity(PhysicsEntity entity){
		physicsentities.add(entity);
	}

	@Override
	public void update(float delta) {
		
		for(PhysicsEntity pe : physicsentities){
			Entity e = (Entity) pe;
			e.update(delta);
		}
		for(PhysicsEntity pe : physicsentities){
			Entity e1 = (Entity) pe;
			for(PhysicsEntity pe2 : physicsentities){
					Entity e2 = (Entity) pe2;
					if(e1 != e2){
						if(e2.collide(e1)!=null){
							//e1.multiplier = 0;
							//e2.multiplier = 0;
							//pe.applyForce(new Vector2D(0, -22));;
							//pe2.applyForce(new Vector2D(0, -22));
						}else{
							e1.multiplier = 1;
							e2.multiplier = 1;
						}
					}
			}
		}
		for(PhysicsEntity pe : physicsentities){
			Entity e = (Entity) pe;
			if(pe.enableGravity()){
				if(e.location.y <= scene.getHeight() - e.height-20){
					pe.applyForce(GRAVITY);
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		for(PhysicsEntity pe : physicsentities){
			Entity e = (Entity) pe;
			e.render(g2d);
		}
	}
}
