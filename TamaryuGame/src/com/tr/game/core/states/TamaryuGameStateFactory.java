package com.tr.game.core.states;

import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.obj.state.TRGameStateFactory;
import com.tr.game.state.help.HelpState;
import com.tr.game.state.island.IslandMainState;
import com.tr.game.state.login.LoginState;
import com.tr.game.state.menue.GameMenueState;
import com.tr.game.state.result.ResultState;
import com.tr.game.state.setting.lang.SettingsLangState;
import com.tr.game.state.test.TamaCombineState;

public class TamaryuGameStateFactory extends TRGameStateFactory {

	public static final int LOGIN_STATE = 0;
	public static final int ISLAND_STATE = 1;
	public static final int DRAGON_STATE = 2;
	public static final int INTERACTION_STATE = 3;
	public static final int BREEDING_STATE = 4;
	public static final int MINIGAME_STATE = 5;
	public static final int RESULT_STATE = 6;
	public static final int SETTING_STATE = 7;
	public static final int SETTING_LANG_STATE = 8;
	public static final int HELP_STATE = 9;

	@Override
	public TRAbstractGameState getState(int id) {
		switch (id) {
		case SETTING_STATE:
			return new GameMenueState();
		case RESULT_STATE:
			return new ResultState();
		case MINIGAME_STATE:
			return new TamaCombineState();
		case BREEDING_STATE:
			return new LoginState();
		case INTERACTION_STATE:
			return new LoginState();
		case DRAGON_STATE:
			return new LoginState();
		case ISLAND_STATE:
			return new IslandMainState();
		case SETTING_LANG_STATE:
			return new SettingsLangState();
		case HELP_STATE:
			return new HelpState();
		case LOGIN_STATE:
		default:
			return new LoginState();
		}
	}

	@Override
	public TRAbstractGameState getDefaultState() {
		return new LoginState();
	}

	@Override
	public TRAbstractGameState getErrorState() {
		// TODO Auto-generated method stub
		return null;
	}

}
