package com.falcron.input.mouse;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public interface MouseMotionInteractable {
	public Rectangle getBounds();
	public void onHover(MouseEvent event);
	public void onDrag(MouseEvent event);
}
