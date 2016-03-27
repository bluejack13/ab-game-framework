package com.falcron.sprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map.Entry;

import com.falcron.core.Renderable;
import com.falcron.core.SimpleScript;
import com.falcron.graphics.scene.SimpleScene;
import com.falcron.math.Vector2D;

public abstract class Entity implements Renderable, Serializable{

	private static final long serialVersionUID = 1L;
	public Vector2D location;
	public Entity parent;
	public int width, height, multiplier;
	public CenterRotation centerRotation;
	public OuterRotation outerRotation;
	public Rotation customRotation;
	public String name, tag;
	public SimpleScene scene;
	public boolean debug = false;
	public boolean render;
	protected BufferedImage image;
	private AffineTransform transform;
	private int id;
	private static int count;
	private int parentX, parentY;
	private HashMap<String, SimpleScript> scripts;
	
	
	public Entity(int width, int height, BufferedImage image, SimpleScene scene){
		this.location = new Vector2D();
		this.width = width;
		this.height = height;
		this.image = image;
		this.scene = scene;
		this.render = true;
		this.multiplier = 1;
		this.centerRotation = new CenterRotation();
		this.outerRotation = new OuterRotation();
		this.customRotation = new Rotation();
		this.scripts = new HashMap<String, SimpleScript>();
		this.tag = "Entity";
		this.id = count;
		count++;
	}
	
	public Entity(BufferedImage image, SimpleScene scene){
		this(image.getWidth(), image.getWidth(), image, scene);
	}
	
	public int getID(){
		return this.id;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)location.x, (int)location.y, width, height);
	}
	
	public Vector2D collide(Entity e){
		int widthrange1 = (int)location.x;
		int widthrange2 = widthrange1 + width;
		int widthrange3 = (int)e.location.x;
		int widthrange4 = widthrange3 + e.width;
		int heightrange1 = (int)location.y;
		int heightrange2 = heightrange1 + height;
		int heightrange3 = (int)e.location.y;
		int heightrange4 = heightrange3 + e.height;
		for(int i=heightrange3;i<heightrange4;i++){
			for(int j=widthrange3;j<widthrange4;j++){
				if((i > heightrange1 && i < heightrange2) &&
						j > widthrange1 && j < widthrange2) return new Vector2D(j, i);
			}
			
		}
		return null;
	}
	
	public void setParent(Entity parent){
		this.parent = parent;
		parentX = (int) (parent.location.x - location.x);
		parentY = (int) (parent.location.y - location.y);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends SimpleScript> T getScript(String sname){
		return (T) scripts.get(sname);
	}
	
	public HashMap<String, SimpleScript> getScripts(){
		return this.scripts;
	}
	
	public void addScript(String sname, Class<? extends SimpleScript> scriptclass){
		try {
			Constructor<?> cn = scriptclass.getConstructor(Renderable.class);
			SimpleScript s = (SimpleScript) cn.newInstance(this);
			scripts.put(sname, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addScript(String sname, String classname){
		try {
			this.addScript(sname, (Class<? extends SimpleScript>) scene.getClass().forName(classname));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void removeScript(String sname){
		scripts.remove(sname);
	}
	
	@Override
	public void update(float delta) {
		for(Entry<String, SimpleScript> en : scripts.entrySet()){
			en.getValue().update(delta);
		}
	}
	
	@Override
	public void render(Graphics2D g2d) {
		if(render){
			transform = g2d.getTransform();
			g2d.rotate(Math.toRadians(customRotation.getRotation()), customRotation.getX(), customRotation.getY());
			g2d.rotate(Math.toRadians(outerRotation.getRotation()), outerRotation.getX(), outerRotation.getY());
			g2d.rotate(Math.toRadians(centerRotation.getRotation()), centerRotation.getX(), centerRotation.getY());
			transformedRender(g2d);
			g2d.setTransform(transform);
			
			for(Entry<String, SimpleScript> en : scripts.entrySet()){
				en.getValue().render(g2d);
			}
		}
		if(debug){
			g2d.setColor(Color.red);
			g2d.drawRect((int)location.x, (int)location.y, width, height);
		}
	}
	
	public void transformedRender(Graphics2D g2d){
		if(image!=null){
			if(parent==null){
				g2d.drawImage(image, (int)location.x, (int)location.y, width, height, null);
			}else{
				g2d.drawImage(image, (int)(parent.location.x + parentX), 
						(int)(parent.location.y + parentY), width, height, null);
			}
		}
	}
	
	public class Rotation implements Serializable{
		private float rotation;
		protected float x, y;
		protected float getX() {
			return x;
		}
		protected float getY() {
			return y;
		}
		public float getRotation(){
			return this.rotation;
		}
		public void setRotation(float degree){
			this.rotation = degree;
		}
		public void add(float degree){
			this.setRotation(rotation+degree);
		}
	}
	
	public class CenterRotation extends Rotation{
		public float getX(){
			return location.x+width/2;
		}
		public float getY(){
			return location.y+height/2;
		}
	}
	
	public class OuterRotation extends CenterRotation{
		@Override
		public float getX() {
			return super.getX()+x;
		}
		@Override
		public float getY() {
			return super.getY()+y;
		}
	}
}
