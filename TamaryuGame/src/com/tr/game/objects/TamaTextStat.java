package com.tr.game.objects;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.gl.TRGLImageView;

public class TamaTextStat extends TRGLImageView {
	private TRLabel name = TRComponentManager.getLabel("Name.:");
	private TRLabel value = TRComponentManager.getLabel("10");
	
	public TamaTextStat(String name, int val){
		this.name.setColor(Color.BLACK);
		this.value.setColor(Color.BLACK);
		
		setName(name);
		setValue(val);
		
		this.addComponent(this.name);
		this.addComponent(this.value);
	}
	
	public void setName(String name){
		this.name.setText(name+":");
		this.reinit();
	}
	
	public void setValue(int val){
		this.value.setText(""+val);
		this.reinit();
	}
	
	private void reinit(){
		int s = name.getWidth();
		this.setSize(s+value.getWidth()+10, name.getHeight());
		name.setZ(this.getPosition().z);
		value.setPosition(s+10, 0, this.getPosition().z+1);
	}
	
	public void setPosition(float x, float y, float z){
		super.setPosition(x, y, z);
		this.reinit();
	}
	
}
