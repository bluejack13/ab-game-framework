package com.falcron.input.mouse;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public interface MouseInteractable {
	public Rectangle getBounds();
	public void onClick(MouseEvent event);
	public void onDoubleClick(MouseEvent event);
	public void onPress(MouseEvent event);
	public void onRelease(MouseEvent event);
	public void onEnter(MouseEvent event);
	public void onExit(MouseEvent event);
}
