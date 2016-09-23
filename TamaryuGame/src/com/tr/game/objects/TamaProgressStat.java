package com.tr.game.objects;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
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
		bar.setMinValue(minVal);
		bar.setMaxValue(maxVal);
		bar.setValue(val);
		
		this.addComponent(this.name);
		this.addComponent(this.maxValue);
		this.addComponent(this.minValue);
		this.addComponent(this.bar.getRenderable());
		
		reinit();
	}
	
	public void 
}
