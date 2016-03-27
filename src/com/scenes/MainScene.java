package com.scenes;

import java.awt.Graphics2D;

import com.falcron.core.SimpleGame;
import com.falcron.graphics.core.SimpleText;
import com.falcron.graphics.scene.SimpleScene;
import com.falcron.sprite.AnimatedSprite;
import com.scripts.GhostScript;
import com.scripts.ScoreScript;

public class MainScene extends SimpleScene{

	SimpleText st;
	AnimatedSprite as, tmp2;
	SimpleText tmp;
	
	public MainScene(SimpleGame game) {
		super(game);
		loadXML("MainScene");
		//tmp = loadXMLObject("myte");
		//tmp2 = loadXMLObject("AnimatedSprite");
		//addRenderable(tmp);
		//addRenderable(tmp2);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		
	}

	@Override
	public void initResource() {
		
	}

}
