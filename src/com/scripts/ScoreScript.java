package com.scripts;
import java.awt.Graphics2D;
import com.falcron.core.Renderable;
import com.falcron.core.SimpleScript;
import com.falcron.graphics.core.SimpleText;
import com.falcron.sprite.Entity;
public class ScoreScript extends SimpleScript{
	
public SimpleText simpletext;
private int score;

   public ScoreScript(Renderable renderable)
   {
      super(renderable);
	simpletext = (SimpleText) entity;
   }

	public void addScore(int score){
		this.score += score;
	}
	
   @Override
   public void update(float delta)
   {
	if(simpletext != null){
      		simpletext.setText(this.score+"");
	}
   }

   @Override
   public void render(Graphics2D g2d)
   {
   }
}








