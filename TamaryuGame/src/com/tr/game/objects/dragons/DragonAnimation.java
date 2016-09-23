package com.tr.game.objects.dragons;

import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.engine.input.TRDroparea;
import com.tr.game.objects.Dragon;
import com.tr.gl.core.GLCamera;

public class DragonAnimation extends TRGLAnimationView implements TRDroparea {
	protected int zIndex = 0; 
	protected int fieldW = 0, fieldH = 0;
	protected Dragon ref = null;

	
	public DragonAnimation(int zIndex, Dragon ref){
		this.zIndex = zIndex;
		this.ref = ref;
	}
	
	public int getFieldWidth(){
		return fieldW;
	}
	
	public int getFieldHeight(){
		return fieldH;
	}
	
	public Dragon getRef(){
		return ref;
	}

	@Override
	public boolean drop(IRenderable o) {
		if(ref != null)
			return ref.onDrop(o);
		return false;
	}
	
	public void render(TRRenderContext s){
		super.render(s);
		fieldW  = (int) (s.getScene().getCamera().getWinWidth() * ((GLCamera) s.getScene().getCamera()).getScale());
		fieldH  = (int) (s.getScene().getCamera().getWinHeigth() * ((GLCamera) s.getScene().getCamera()).getScale());
	}

}
