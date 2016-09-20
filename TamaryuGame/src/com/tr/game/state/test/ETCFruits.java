package com.tr.game.state.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.tr.engine.img.TRImage;

public enum ETCFruits {
	APPLE(new TRImage("apple1", "golden_apple_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	CHICKEN(new TRImage("chicken1", "chicken_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	MEAT(new TRImage("meat1", "meat_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	ORANGE(new TRImage("orange1", "orange_64 (2)", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	PEAR(new TRImage("pear1", "pear2_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	PITAHAYA(new TRImage("pitahaya1", "pitahaya_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	TOMATO(new TRImage("tomato1", "tomato_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64));
	
	private TRImage img = null;
	
	private static final List<ETCFruits> VALUES =  Collections.unmodifiableList(Arrays.asList(values()));
		  private static final int SIZE = VALUES.size();
		  private static final Random RANDOM = new Random();

	ETCFruits(TRImage img){
		this.img = img;
	}
	
	public TRImage getImg(){
		return this.img;
	}
	

	public static ETCFruits random()  {
	    return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
