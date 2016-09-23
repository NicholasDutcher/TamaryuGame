package com.tr.game.state.tc;

import java.util.ArrayList;

public class TCChain {
	public static final int V_CHAIN = 0;
	public static final int H_CHAIN = 1;
	
	public ETCFruits type = null;
	public int chainType = -1;
	
	public ArrayList<TCFruit> fruits = new ArrayList<TCFruit>();
	
	
	public void addFruit(TCFruit f){
		fruits.add(f);
	}
	
	public int getLength(){
		return fruits.size();
	}

}
