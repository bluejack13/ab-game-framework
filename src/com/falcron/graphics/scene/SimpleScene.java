package com.falcron.graphics.scene;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import com.ab2ds.util.EngineParser;
import com.ab2ds.util.XMLEngine;
import com.falcron.core.Renderable;
import com.falcron.core.SimpleGame;
import com.falcron.core.SimpleScript;
import com.falcron.graphics.core.SimpleText;
import com.falcron.input.mouse.Mouse;
import com.falcron.sprite.AnimatedSprite;
import com.falcron.sprite.Entity;
import com.falcron.util.Image;

public abstract class SimpleScene extends Canvas implements Runnable, Renderable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH, HEIGHT;
	private int fps, ups;
	
	public Thread thread;
	public boolean running;
	
	protected SimpleGame game;
	private BufferedImage pixels;
	
	protected Vector<Renderable> renderables;
	protected Mouse mouse;
	
	public SimpleScene(SimpleGame game) {
		this(game, game.getWidth(), game.getHeight());
		mouse = new Mouse();
		addMouseListener(mouse);
	}
	
	public SimpleScene(SimpleGame game, int width, int height) {
		this.game = game;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.running = false;
		this.pixels = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.renderables = new Vector<Renderable>();
		setSize(WIDTH, HEIGHT);
		setBackground(Color.black);
		initResource();
	}
	
	public void addScript(Entity entity, String cname, String classname){
		entity.addScript(cname, EngineParser.getClassByName(classname, getClass()));
	}
	
	public void loadXML(File f, ClassLoader loader){
		XMLEngine xml = new XMLEngine(f);
		renderables.addAll(EngineParser.loadSimpleTexts(xml, this, loader));
		renderables.addAll(EngineParser.loadAnimatedSprites(xml, this, loader));
	}
	
	public void registerMouseInteractable(){
		for(Renderable r : renderables){
			if(r instanceof Entity){
				HashMap<String, SimpleScript> maps = ((Entity)r).getScripts();
				for(Entry<String, SimpleScript> en : maps.entrySet()){
					mouse.addInteractable(en.getValue());
				}
			}
		}
	}
	
	public void loadXML(String name){
		loadXML(name, getClass().getClassLoader());
	}
	
	public void loadXML(String name, ClassLoader loader){
		XMLEngine xml = new XMLEngine(getXMLScene(name));
		renderables.addAll(EngineParser.loadAnimatedSprites(xml, this, loader));
		renderables.addAll(EngineParser.loadSimpleTexts(xml, this, loader));
		registerMouseInteractable();
	}
	
	public <T extends Entity> T loadXMLObject(File f, ClassLoader loader){
		Renderable r = null;
		XMLEngine xml = new XMLEngine(f);
		
		Vector<SimpleText> v1 = EngineParser.loadSimpleTexts(xml, this, loader);
		if(v1.size() > 0){
			r = v1.get(0);
		}else{
			Vector<AnimatedSprite> v2 = EngineParser.loadAnimatedSprites(xml, this, loader);
			r = v2.get(0);
		}
		return (T) r;
	}
	
	public <T extends Entity> T loadXMLObject(String name){
		return loadXMLObject(name, getClass().getClassLoader());
	}
	
	public <T extends Entity> T loadXMLObject(String name, ClassLoader loader){
		Renderable r = null;
		XMLEngine xml = new XMLEngine(getXMLObject(name));
		
		Vector<SimpleText> v1 = EngineParser.loadSimpleTexts(xml, this, loader);
		if(v1.size() > 0){
			r = v1.get(0);
		}else{
			Vector<AnimatedSprite> v2 = EngineParser.loadAnimatedSprites(xml, this, loader);
			r = v2.get(0);
		}
		return (T) r;
	}
	
	public InputStream getXMLObject(String name){
		return SimpleScene.class.getResourceAsStream("/com/objects/"+name+".xml");
	}
	
	public InputStream getXMLScene(String name){
		return SimpleScene.class.getResourceAsStream("/com/scenes/"+name+".xml");
	}
		
	public void addRenderable(Renderable renderable){
		renderables.add(renderable);
	}
	
	public Entity getEntity(String name){
		for(Renderable r : renderables){
			if(((Entity)r).name.equals(name)) return (Entity)r;
		}
		return null;
	}
	
	public <T> T getEntity(Class<T> cls){
		for(Renderable r : renderables){
			if(cls.getName().equals(r.getClass().getName())){
				return (T) r;
			}
		}
		return null;
	}
	
	public <T> Vector<T> getEntities(Class<T> cls){
		Vector<T> v = new Vector<T>();
		for(Renderable r : renderables){
			if(cls.getName().equals(r.getClass().getName())){
				v.add((T) r);
			}
		}
		return v;
	}
	
	
	public void deleteRenderable(Renderable r){
		synchronized (renderables) {
			renderables.remove(r);
		}
	}
	
	public synchronized void start(){
		this.running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}
	public synchronized void stop(){
		this.running = false;
		try {
			thread.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	public abstract void update(float delta);
	public abstract void render(Graphics2D g2d);
	public abstract void initResource();
	
	private void initUpdate(float delta){
		for(Renderable r : renderables){
			r.update(delta);
		}
		update(delta);
	}
	
	public int[] getData(){
		return Image.getData(pixels);
	}
	
	private void initRender(){
		BufferStrategy b = getBufferStrategy();
		if(b==null){
			createBufferStrategy(3);
			return;
		}
		Graphics2D g2d = (Graphics2D) b.getDrawGraphics();
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		synchronized (renderables) {
			for(Renderable r : renderables){
				r.render(g2d);
			}
		}
		g2d.setColor(Color.white);
		render(g2d);
		g2d.drawImage(pixels, 0, 0, null);
		g2d.dispose();
		b.show();
	}
	
	
	public synchronized int getFPS(){return this.fps;}
	public synchronized int getUPS(){return this.ups;}
	

	@Override
	public void run() {
			long last = System.nanoTime();
			long lastms = System.currentTimeMillis();
			double ns = 1000000000.0/60.0;
			double delta = 0;
			int updates = 0, frames=0;
			
			while(running){
				long now = System.nanoTime();
				delta += (now - last) / ns;
				last = now;
				while(delta > 1){
					initUpdate((float)delta);
					updates++;
					delta--;
				}
				if(System.currentTimeMillis() - lastms > 1000){
					this.ups = updates;
					this.fps = frames;
					lastms += 1000;
					updates = frames = 0;
				}
				initRender();
				frames++;
			}
	}
	
	@Override
	public synchronized void addKeyListener(KeyListener l) {
		super.addKeyListener(l);
		game.addKeyListener(l);
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		super.addMouseListener(l);
		game.addMouseListener(l);
	}
	
	@Override
	public synchronized void addMouseMotionListener(MouseMotionListener l) {
		super.addMouseMotionListener(l);
		game.addMouseMotionListener(l);
	}
	
	
	
}
