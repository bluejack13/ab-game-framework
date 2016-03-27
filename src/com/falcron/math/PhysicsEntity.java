package com.falcron.math;


public interface PhysicsEntity {
	public void applyForce(Vector2D force);
	public void applyForce(Vector2D force, Vector2D velocity);
	public boolean enableGravity();
}
