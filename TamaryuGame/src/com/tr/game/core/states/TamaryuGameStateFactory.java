package com.tr.game.core.states;

import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.obj.state.TRGameStateFactory;
import com.tr.game.state.menue.GameMenueState;

public class TamaryuGameStateFactory extends TRGameStateFactory {
	
	public static final int LOGIN_STATE = 0;
	public static final int ISLAND_STATE = 1;
	public static final int DRAGON_STATE = 2;
	public static final int INTERACTION_STATE = 3;
	public static final int BREEDING_STATE = 4;
	public static final int MINIGAME_STATE = 5;
	public static final int RESULT_STATE = 6;
	public static final int SETTING_STATE  = 7;

	@Override
	public TRAbstractGameState getState(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TRAbstractGameState getDefaultState() {
		return new GameMenueState();
	}

	@Override
	public TRAbstractGameState getErrorState() {
		// TODO Auto-generated method stub
		return null;
	}

}
