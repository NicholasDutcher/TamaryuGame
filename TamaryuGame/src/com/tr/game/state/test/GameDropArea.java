package com.tr.game.state.test;

import com.jogamp.opengl.util.packrect.Rect;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.input.ITRMouseListener;
import com.tr.engine.input.TRDroparea;
import com.tr.engine.input.TRMouseEvent;

public class GameDropArea extends TRGLImageView implements ITRMouseListener, TRDroparea {

	@Override
	public void drop(IRenderable o) {
		// TODO Auto-generated method stub

	}

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHitbox(Rect hitbox) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isHit(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IRenderable getSrc() {
		// TODO Auto-generated method stub
		return null;
	}

}
