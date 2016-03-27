package com.falcron.math;

import java.io.Serializable;



public final class Vector2D implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public float x, y;
	
	public Vector2D() {
		
	}

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D setX(float x){
		this.x = x;
		return this;
	}
	
	public Vector2D setY(float y){
		this.y = y;
		return this;
	}
	
	public Vector2D negate(){
		if(x > 0) x *= -1;
		if(y > 0) y *= -1;
		return this;
	}
	
	public Vector2D add(float x, float y){
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2D add(Vector2D v2d){
		this.x += v2d.x;
		this.y += v2d.y;
		return this;
	}
	
	public Vector2D sub(float x, float y){
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public static Vector2D sub(Vector2D v1, Vector2D v2){
		return new Vector2D(v1.x-v2.x, v1.y-v2.y);
	}
	
	public Vector2D mult(float f){
		this.x *= f;
		this.y *= f;
		return this;
	}
	
	public Vector2D div(float f){
		this.x /= f;
		this.y /= f;
		return this;
	}
	
	public float mag(){
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector2D normalize(){
		float mag = mag();
		if(mag != 0){
			div(mag);
		}
		return this;
	}
	
	public void limit(float scalar){
		if(mag() > scalar){
			normalize();
			mult(scalar);
		}
	}

	public Vector2D clone(){
		return new Vector2D(x, y);
	}
	
	public static Vector2D lerp(Vector2D from, Vector2D to, float amount){
		Vector2D tmp = new Vector2D();
		tmp = from.clone();
		if(from.x > to.x){
			tmp.x -= amount;
		}
		if(from.x < to.x){
			tmp.x += amount;
		}
		if(from.y > to.y){
			tmp.y -= amount;
		}
		if(from.y < to.y){
			tmp.y += amount;
		}
		return tmp;
	}
}
