package com.tr.game.state.tc;

import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.input.TRDroparea;

public class GameDropArea extends TRGLImageView implements  TRDroparea {
	private TCDropListener dl = null;
	public int col = 0;
	public int row = 0;
	
	public GameDropArea(int size){
		this.setImage(new TRImage("game1BG", "game_bg", "png", "/img", 0, 0, 0, 36, 36, 36, 36));
		this.setSize(size, size);
	}
	
	public void setDropListener(TCDropListener dl){
		this.dl = dl;
	}

	@Override
	public boolean drop(IRenderable o) {
		if(o instanceof TCFruit && dl != null){
			return dl.drop(col, row, (TCFruit)o);
		}
		return false;
	}


}
