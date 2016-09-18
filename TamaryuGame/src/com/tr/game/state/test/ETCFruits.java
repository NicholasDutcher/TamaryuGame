package com.tr.game.state.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.tr.engine.img.TRImage;

public enum ETCFruits {
	APPLE(new TRImage("pear1", "pear_32", "png", "/img", 0, 0, 0, 32, 32, 32, 32)),
	PEAR(new TRImage("apple1", "apple", "png", "/img", 0, 0, 0, 300, 300, 300, 300)),
	MEAT(new TRImage("meat1", "meat_128", "png", "/img", 0, 0, 0, 128, 128, 128, 128)),
	ORANGE(new TRImage("orange1", "orange_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64)),
	NOTHING(new TRImage("fail1", "fail_128", "png", "/img", 0, 0, 0, 128, 128, 128, 128));
	
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
