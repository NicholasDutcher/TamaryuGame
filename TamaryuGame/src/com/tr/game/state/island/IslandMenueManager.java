package com.tr.game.state.island;

import com.tr.engine.grf.IRenderable;

public class IslandMenueManager {
	public static final int MENUE = 0;
	public static final int STATS = 1;
	public static final int INVENTORY = 2;
	
	private IRenderable cur = null;
	private IslandMenue menu = null;
	
	private int curState = -1;
	
	public IslandMenueManager(IslandMenue m){
		this.menu = m;
		loadState(MENUE);
	}
	
	public void loadState(int c){
		if(curState == c)
			return;
		curState = c;
		if(cur != null)
			menu.removeContent(cur);
		switch(curState){
		case MENUE: 
			menu.setContent(new IslandTextMenue(this));
			break;
		case STATS:
			break;
		case INVENTORY: 
			break;
		}
		menu.setContent(cur);
	}
	

}
