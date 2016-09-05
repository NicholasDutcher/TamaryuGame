package com.tr.game.state.menue;

import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.gl.core.GLCamera;

public class MenueBackground extends TRGLImageView{

	public MenueBackground(){
		super(new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		this.setPosition(0, 0, -10);
	}
	
	public void init(TRRenderContext rc){
		super.init(rc);
		this.setFixedPosition(FIXED_POS_CENTER);
		resize(rc, 0, 0);
	}
	
	public void resize(TRRenderContext rc, int w, int h){
		GLCamera cam = (GLCamera)rc.getScene().getCamera();

		int rw = (int) cam.getRefWidth(), rh = (int) cam.getRefHeight();
		if(rw > this.width || rh > this.height){
			if(this.width - rw < this.height - rh){
				float f = this.height/this.width;
				this.setSize(rw, Math.round(rw*f));
			}else{
				float f = this.height/this.width;
				this.setSize(Math.round(rh*f), rh);
			}
		}
		
		super.resize(rc, (int)cam.getWinWidth(), (int)cam.getWinHeigth());
		
		/*float x = 0, y = 0;
		x = -Math.round((this.width-rw)/2f);
		y = -Math.round((this.height-rh));
		this.setPosition(x, y, this.getPosition().z);*/
	}
}
