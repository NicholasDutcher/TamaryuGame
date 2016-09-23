package com.tr.game.objects;

import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;

public class TamaProgressBar extends TRGLImageView{
	protected float maxValue = 100;
	protected float minValue = 0;
	protected float curValue = 50;
	protected float tmpValue = 0;
	
	protected float stepP = 0.02f;
	protected float step = 0;
	
	protected TRGLImageView value = new TRGLImageView();
	
	public TamaProgressBar(float minValue, float maxValue) {
		this.setImage(new TRImage("progressBG", "progress_bg2", "png", "/img", 0, 0, 0, 330, 34, 330, 34));
		value.setImage(new TRImage("progress", "progress1", "png", "/img", 0, 0, 0, 0, 34, 330, 34));
		
		calculateStep();
		
		this.addComponent(value);
	}
	
	protected void calculateStep(){
		step = (maxValue - minValue) * stepP;
	}
	
	public void setStepSize(float f){
		stepP = f;
		calculateStep();
	}
	
	public IRenderable getRenderable(){
		return this;
	}
	
	public void setPosition(int x, int y, int z){
		super.setPosition(x, y, z);
		value.setPosition(0, 0, z+1);
	}
	
	public void setSize(int w, int h){
		value.setSize(w, h);
		super.setSize(w, h);
	}
	
	
	public void setValue(float v){
		curValue = v;
	}
	
	public float getCurValue(){
		return curValue;
	}
	
	public void setMinValue(float v){
		minValue = v;
		calculateStep();
	}
	
	public float getMinValue(){
		return minValue;
	}
	
	public void setMaxValue(float v){
		maxValue = v;
		calculateStep();
	}
	
	public float getMaxValue(){
		return maxValue;
	}
	
	public boolean increaseValue(float vd){
		curValue = Math.min(maxValue, curValue + vd);
		
		return (curValue == maxValue);
	}
	
	public boolean decreaseValue(float vd){
		curValue = Math.max(minValue, curValue - vd);
		
		return (curValue == minValue);
	}


	@Override
	public void render(TRRenderContext rc) {
		super.render(rc);
		if(tmpValue != curValue){
			if(tmpValue < curValue){
				tmpValue = Math.min(curValue, tmpValue + step);
			}else{
				tmpValue = Math.max(curValue, tmpValue - step);
			}
			
			float p = (tmpValue - minValue) / (maxValue - minValue);
			
			value.setImage(new TRImage("progress", "progress1", "png", "/img", 0, 0, 0, Math.round(p*330), 34, 330, 34));
			value.setSize(Math.round(p*this.getWidth()), this.getHeight());
		}
	}

}
