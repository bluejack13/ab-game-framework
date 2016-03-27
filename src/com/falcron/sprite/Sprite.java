package com.falcron.sprite;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.falcron.graphics.scene.SimpleScene;
import com.falcron.math.PhysicsEntity;
import com.falcron.math.Vector2D;

public abstract class Sprite extends Entity implements PhysicsEntity{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector2D velocity, acceleration;
	public float mass;
	
	public Sprite(int width, int height, BufferedImage image, SimpleScene scene) {
		super(width, height, image, scene);
		this.velocity = new Vector2D(1,1);
		this.acceleration = new Vector2D();
		this.mass = 5;
	}

	public Sprite(BufferedImage image, SimpleScene scene) {
		this(image.getWidth(), image.getHeight(), image, scene);
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		acceleration.limit(10);
		velocity.limit(5);
		acceleration.mult(0);
	}
	
	@Override
	public void render(Graphics2D g2d) {
		super.render(g2d);
	}

	@Override
	public void applyForce(Vector2D force) {
		applyForce(force, this.velocity);
	}
	
	@Override
	public void applyForce(Vector2D force, Vector2D velocity) {
		acceleration.add(force.clone());
		acceleration.div(mass);
		velocity.add(acceleration);
		location.add(velocity.mult(multiplier));
	}
	
	@Override
	public boolean enableGravity() {
		return true;
	}
}
