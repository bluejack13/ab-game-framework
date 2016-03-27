package com.falcron.sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map.Entry;

public class Healthbar extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int health, maxHealth;
	protected int height;
	protected Color color;
	private HashMap<HealthRange, Color> maps = new HashMap<HealthRange, Color>();
	
	public Healthbar(Entity owner, int maxHealth){
		super(0, 0, null, owner.scene);
		this.health = this.maxHealth = maxHealth;
		this.height = 5;
		this.parent = owner;
		this.color = Color.GREEN;
		addColorMapping(new HealthRange(0, 20), Color.red);
		addColorMapping(new HealthRange(21, 50), Color.orange);
		addColorMapping(new HealthRange(51, 100), Color.green);
	}
	
	public Healthbar(Entity owner){
		this(owner, 100);
	}
	
	public void addColorMapping(HealthRange range, Color color){
		maps.put(range, color);
	}
	
	public void addHealth(int n){
		if(this.health + n > maxHealth){
			this.health = maxHealth;
		}else{
			this.health += n;
		}
	}
	
	public void subHealth(int n){
		if(this.health - n < 0){
			this.health = 0;
		}else{
			this.health -= n;
		}
	}
	
	public int getHealth(){
		return this.health;
	}
	
	@Override
	public void update(float delta) {
		for(Entry<HealthRange, Color> entry : maps.entrySet()){
			if(health >= entry.getKey().min && health <= entry.getKey().max){
				color = entry.getValue();
			}
		}
	}
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.fillRect(
				(int)(parent.location.x),
				(int)(parent.location.y-parent.height/2),
				parent.width * health/maxHealth,
				height);
	}
	
	private class HealthRange{
		public int min, max;
		public HealthRange(int min, int max){
			this.min = min; this.max = max;
		}
	}
}
