package com.tr.game.core.states;

import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.obj.state.TRGameStateFactory;
import com.tr.game.state.menue.GameMenueState;

public class TamaryuGameStateFactory extends TRGameStateFactory {
	
	public static final int START_MENU_STATE = 0;
	public static final int GAME_STATE = 1;
	public static final int LANGUAGE_STATE = 2;
	public static final int HELP_STATE = 3;
	public static final int TEST_STATE = 4;
	public static final int INVENTORY_TEST_STATE = 5;

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
