package com.scripts;
import java.awt.Graphics2D;
import com.falcron.core.Renderable;
import com.falcron.core.SimpleScript;
import com.falcron.graphics.core.SimpleText;
import com.falcron.sprite.Entity;
import java.awt.event.*;
public class GhostScript extends SimpleScript{

	ScoreScript score;

   public GhostScript(Renderable renderable)
   {
	   super(renderable);
	   
   }

   @Override
   public void update(float delta)
   {
	   SimpleText st = (SimpleText) scene.getEntity("scoretxt");
	   score = st.getScript("ScoreScript");
   }

   @Override
   public void render(Graphics2D g2d)
   {
	   
   }

   @Override
   public void onClick(MouseEvent e){
	   score.addScore(10);
   }
}



