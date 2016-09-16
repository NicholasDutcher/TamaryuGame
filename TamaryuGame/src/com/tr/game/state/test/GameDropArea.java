package com.tr.game.state.test;

import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.input.TRDroparea;

public class GameDropArea extends TRGLImageView implements  TRDroparea {
	
	public GameDropArea(int size){
		this.setImage(null);
		this.setSize(size, size);
	}

	@Override
	public boolean drop(IRenderable o) {
		// TODO Auto-generated method stub
		return false;
	}


}
