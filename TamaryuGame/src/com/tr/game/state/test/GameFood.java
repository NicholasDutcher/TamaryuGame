package com.tr.game.state.test;

import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;

public class GameFood extends TRGLImageView {
	protected int tposX = 0, tposY = 0;
	protected int maxSpeed = 10;
	protected boolean onPlace = true;
	
	public GameFood(TRImage img){
		this.setImage(img);
	}

	/*@Override
	public void onDrag() {
		this.setZ(this.getPosition().z+1);
		this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 3, 57/255f, 255/255f, 20/255f, 0));
		//System.out.println("Start: "+this.getAbsolutPosition()+" ["+this.getAbsolutScale()+"]");
		//System.out.println("Size: "+this.getWidth()+" x "+this.getHeight()+" Hitbox: "+hitbox);
	}

	@Override
	public void onDrop() {
		this.setZ(this.getPosition().z-1);
		this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 0, 0, 0, 0, 0));
	}*/
	
	public void setTargetPos(int x, int y){
		tposX = x;
		tposY = y;
		if(this.getPosition().x != tposX || this.getPosition().y != tposY){
			onPlace = false;
		}
	}
	
	public boolean update(){
		int xOff = 0, yOff = 0;
		if(this.getPosition().x != tposX){
			xOff = (int) (tposX - this.getPosition().x);
			if(xOff < 0){
				xOff = Math.max(xOff, -maxSpeed);
			}else{
				xOff = Math.min(xOff, maxSpeed);
			}
		}
		
		if(this.getPosition().y != tposY){
			yOff = (int) (tposY - this.getPosition().y);
			yOff = Math.min(yOff, maxSpeed);
			if(yOff < 0){
				yOff = Math.max(yOff, -maxSpeed);
			}else{
				yOff = Math.min(yOff, maxSpeed);
			}
		}
		this.increasePos(xOff, yOff, 0);
		
		if(xOff == 0 && yOff == 0)
			onPlace = true;
		
		return onPlace;
	}

}
