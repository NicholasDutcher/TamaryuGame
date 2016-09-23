package com.tr.game.objects;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.gl.TRGLImageView;

public class TamaProgressStat extends TRGLImageView{
	private TRLabel name = TRComponentManager.getLabel("Name.:");
	private TRLabel minValue = TRComponentManager.getLabel("0");
	private TRLabel maxValue = TRComponentManager.getLabel("10");
	private TamaProgressBar bar = new TamaProgressBar(0, 10);
	
	public TamaProgressStat(String name, float minVal, float maxVal, float val){
		this.name.setText(name);
		this.minValue.setText(""+minVal);
		this.maxValue.setText(""+maxVal);
		this.name.setColor(Color.BLACK);
		this.minValue.setColor(Color.BLACK);
		this.maxValue.setColor(Color.BLACK);
		
		bar.setMinValue(minVal);
		bar.setMaxValue(maxVal);
		bar.setValue(val);
		
		this.addComponent(this.name);
		this.addComponent(this.maxValue);
		this.addComponent(this.minValue);
		this.addComponent(this.bar.getRenderable());
		this.setSize(200, 38+25);
		
		reinit();
	}
	
	public void setName(String s){
		this.name.setText(s);
	}
	
	public void setValue(float f){
		this.bar.setValue(f);
	}
	
	public void setMinValue(float v){
		this.bar.setMaxValue(v);
		this.minValue.setText(""+(int)v);
	}
	
	public void setMaxValue(float v){
		this.bar.setMaxValue(v);
		this.maxValue.setText(""+(int)v);
	}
	
	public void reinit(){
		this.name.setPosition(0, 25, this.getPosition().z+1);
		this.minValue.setScale(0.6f);
		this.minValue.setPosition(5, 6, this.getPosition().z+1);
		this.bar.setSize(120, 20);
		this.bar.setPosition(25, 5, (int) (this.getPosition().z+1));
		this.maxValue.setScale(0.6f);
		this.maxValue.setPosition(160/0.6f, 6, this.getPosition().z+1);
	}
	
	public void setPosition(float x, float y, float z){
		super.setPosition(x, y, z);
		reinit();
	}
}
