package com.tr.game.objects;

import com.jogamp.opengl.util.packrect.Rect;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.input.ITRMouseListener;
import com.tr.engine.input.TRDragable;
import com.tr.engine.input.TRMouseEvent;

public abstract class DragableIcon extends TRGLImageView implements TRDragable, ITRMouseListener{
	
	protected Rect hitbox = new Rect(0,0,0,0,null);

	@Override
	public void mouseEnter(TRMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseLeave(TRMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseRelease(TRMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePress(TRMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(TRMouseEvent tre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(TRMouseEvent tre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rect getHitbox() {
		return this.hitbox;
	}

	@Override
	public void setHitbox(Rect hitbox) {
		this.hitbox = hitbox;
	}

	@Override
	public int getZ() {
		return (int) this.getPosition().z;
	}

	@Override
	public boolean isHit(int x, int y) {
		if(x >= getAbsolutPosition().x+hitbox.x() && x <= getAbsolutPosition().x+hitbox.x()+hitbox.maxX()){
			if(y >= getAbsolutPosition().y+hitbox.y() && y <= getAbsolutPosition().y+hitbox.y()+hitbox.maxY()){
				//System.out.println("Hit a Component!");
				return true;
			}
		}
		return false;
	}

	@Override
	public IRenderable getSrc() {
		return this;
	}

	public void setSize(int w, int h){
		super.setSize(w, h);
		setHitbox(new Rect((int) this.getPosition().x, (int) this.getPosition().y, w, h, null));
	}

}
