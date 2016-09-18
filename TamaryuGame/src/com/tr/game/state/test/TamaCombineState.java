package com.tr.game.state.test;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.game.core.states.TamaryuGameStateFactory;

public class TamaCombineState extends TRAbstractGameState {
	private TamaCombine game  = null;

	public TamaCombineState() {
		super(TamaryuGameStateFactory.MINIGAME_STATE, "TAMA_COMBINE");
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		game = new TamaCombine((int) scene.getCamera().getReferenceSize().getHeight());
		scene.clearScene();
		gl.clear();
		scene.addComponent(game.getField());
		scene.addGlobalMouseListener(game);
		gl.add(game);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		scene.removeComponent(game.getField());
		gl.remove(game);
	}

}
