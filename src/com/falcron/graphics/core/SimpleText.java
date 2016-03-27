package com.falcron.graphics.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.falcron.graphics.scene.SimpleScene;
import com.falcron.math.Vector2D;
import com.falcron.sprite.Entity;

public class SimpleText extends Entity{
	
	private static final long serialVersionUID = 1L;
	public Color color;
	private Font font;
	private String text;
	
	public SimpleText(String text, Color color, Vector2D location, Font font, SimpleScene scene) {
		super((int) (text.length()*font.getSize2D()), 30, null, scene);
		this.text = text;
		this.color = color;
		this.location = location;
		this.font = font;
	}
	
	public Font getFont(){
		return this.font;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public SimpleText(String text, SimpleScene scene){
		this(text, new Font("Arial Black", Font.PLAIN, 14), scene);
	}
	
	public SimpleText(String text, Font font, SimpleScene scene){
		this(text, Color.WHITE, new Vector2D(), font, scene);
	}
	
	public void setFontStyle(int style){
		this.font = new Font(font.getFontName(), style, font.getSize());
	}
	
	public void setFontFamily(String font){
		this.font = new Font(font, this.font.getStyle(), this.font.getSize());
	}
	
	public void setFontSize(int size){
		this.font = new Font(this.font.getFontName(), this.font.getStyle(), size);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
	}
	
	@Override
	public void render(Graphics2D g2d) {
		super.render(g2d);
	}
	
	@Override
	public void transformedRender(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.setFont(font);
		FontMetrics met = g2d.getFontMetrics();
		int hgt = met.getHeight();
		int wgt = met.stringWidth(text);
		width = wgt+2;
		height = hgt+2;
		g2d.drawString(text, location.x, location.y+height/1.5f);
	}

}
