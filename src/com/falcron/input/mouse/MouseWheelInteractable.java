package com.falcron.input.mouse;

import java.awt.Rectangle;
import java.awt.event.MouseWheelEvent;

public interface MouseWheelInteractable {
	public Rectangle getBounds();
	public void onWheel(MouseWheelEvent event);
}
