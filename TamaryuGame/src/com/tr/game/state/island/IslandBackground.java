package com.tr.game.state.island;

import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;

public class IslandBackground extends TRGLImageView{
	
	public IslandBackground(){
		this.setZ(0);
		this.setImage(new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
	}
}
