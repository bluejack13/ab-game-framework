package com.ab2ds.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public final class ScriptGenerator {
	
	private static ScriptGenerator sgen;
	
	private ScriptGenerator(){
		
	}
	
	public static ScriptGenerator getInstance(){
		if(sgen == null){
			sgen = new ScriptGenerator();
		}
		return sgen;
	}
	
	public void generateSimpleScriptChild(String childname, File path){
		File f = new File(path+"/"+childname+".java");
		try {
			f.createNewFile();
			PrintWriter pw = new PrintWriter(f);
			pw.println("package com.scripts;");
			pw.println("import java.awt.Graphics2D;");
			pw.println("import com.falcron.core.Renderable;");
			pw.println("import com.falcron.core.SimpleScript;");
			pw.println("import com.falcron.sprite.Entity;");
			pw.println("public class "+childname+" extends SimpleScript{");
			pw.println("   public "+childname+"(Renderable renderable)");
			pw.println("   {");
			pw.println("      super(renderable);");
			pw.println("   }");
			pw.println();
			pw.println("   @Override");
			pw.println("   public void update(float delta)");
			pw.println("   {");
			pw.println("      System.out.println(\"Script update\");");
			pw.println("   }");
			pw.println();
			pw.println("   @Override");
			pw.println("   public void render(Graphics2D g2d)");
			pw.println("   {");
			pw.println("   }");
			pw.println("}");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
