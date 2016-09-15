package com.tr.game.objects;

import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;

public class TamaryuLogo extends TRGLImageView {
	
	public TamaryuLogo(){
		this.setImage(new TRImage("tama_logo", "logo", "png", "/img", 0, 0, 0, 800, 374, 800, 374));
	}

}
