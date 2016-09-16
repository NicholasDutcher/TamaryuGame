package com.tr.game.objects.dragons;

import com.tr.engine.grf.Color;
import com.tr.game.objects.Dragon;

import demo.tama.BabyWyvern;

public class Wyvern extends Dragon {
	protected Color curEyeColor = null;
	protected float colorStep = 0.5f;
	
	protected boolean blink = true;
	protected int minBlinkInterval = 500;
	protected int maxBlinkInterval = 3000;
	protected long nextBlink = 0;
	
	public Wyvern(){
		super();
		this.setImage(new BabyWyvern(1, this));
	}
	
	protected void roundAction(long time){
		// override me
	}
	
	public void updateAction(long time){
		//System.out.println("UPDATE");
		if(this.blink){
			if((nextBlink - time) < 0){
				this.blink();
				nextBlink = time + Math.round(Math.random() * (maxBlinkInterval - minBlinkInterval) + minBlinkInterval);
			}
		}
		// override me
	}
	


}
