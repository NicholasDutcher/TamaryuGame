package com.tr.game.state.test;

import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.img.TRImage;
import com.tr.game.objects.DragableIcon;

public class GameFood extends DragableIcon {
	protected int tposX = 0, tposY = 0;
	protected int maxSpeed = 20;
	protected boolean onPlace = true;
	
	public GameFood(String foodImgName){
		this.setImage(new TRImage(foodImgName, foodImgName, "png", "/img", 0, 0, 0, 32, 32, 32, 32));
	}

	@Override
	public void onDrag() {
		this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_OUTLINES, 1, 0, 0, 0));
	}

	@Override
	public void onDrop() {
		this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_OUTLINES, 0, 0, 0, 0));
	}
	
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
			xOff = Math.min(xOff, maxSpeed);
		}
		
		if(this.getPosition().y != tposY){
			yOff = (int) (tposY - this.getPosition().y);
			yOff = Math.min(yOff, maxSpeed);
		}
		this.increasePos(xOff, yOff, 0);
		
		if(xOff == 0 && yOff == 0)
			onPlace = true;
		
		return onPlace;
	}

}
