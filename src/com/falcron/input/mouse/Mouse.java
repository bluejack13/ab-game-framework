package com.falcron.input.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

public final class Mouse implements MouseMotionListener, MouseListener, MouseWheelListener{

	private Vector<MouseInteractable> interacts;
	private Vector<MouseMotionInteractable> motionInteracts;
	private Vector<MouseWheelInteractable> wheelInteracts;
	
	public Mouse() {
		this.interacts = new Vector<MouseInteractable>();
		this.motionInteracts = new Vector<MouseMotionInteractable>();
		this.wheelInteracts = new Vector<MouseWheelInteractable>();
	}
	
	public void addInteractable(MouseInteractable me){
		interacts.add(me);
	}
	
	public void addMotionInteractable(MouseMotionInteractable mm){
		motionInteracts.add(mm);
	}
	
	public void addWheelInteractable(MouseWheelInteractable mw){
		wheelInteracts.add(mw);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			for(MouseInteractable m : interacts){
				if(m.getBounds().contains(e.getPoint())){
					if(e.getClickCount() == 2){
						m.onDoubleClick(e);
					}else{
						m.onClick(e);
					}
				}
			}
	}

	@Override
	public void mousePressed(MouseEvent e) {
			for(MouseInteractable m : interacts){
				if(m.getBounds().contains(e.getPoint())){
					m.onPress(e);
				}
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
			for(MouseInteractable m : interacts){
				if(m.getBounds().contains(e.getPoint())){
					m.onRelease(e);
				}
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for(MouseInteractable m : interacts){
			if(m.getBounds().contains(e.getPoint())){
				m.onEnter(e);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(MouseInteractable m : interacts){
			if(m.getBounds().contains(e.getPoint())){
				m.onExit(e);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for(MouseMotionInteractable m : motionInteracts){
			if(m.getBounds().contains(e.getPoint())){
				m.onDrag(e);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(MouseMotionInteractable m : motionInteracts){
			if(m.getBounds().contains(e.getPoint())){
				m.onHover(e);
			}
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		for(MouseWheelInteractable m : wheelInteracts){
			if(m.getBounds().contains(e.getPoint())){
				m.onWheel(e);
			}
		}
	}
	
}
