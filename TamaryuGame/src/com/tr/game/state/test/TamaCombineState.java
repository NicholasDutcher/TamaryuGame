package com.tr.game.state.test;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.game.core.states.TamaryuGameStateFactory;

public class TamaCombineState extends TRAbstractGameState {
	private TamaCombine game  = null;
	private TRGLImageView bg = null;

	public TamaCombineState() {
		super(TamaryuGameStateFactory.MINIGAME_STATE, "TAMA_COMBINE");
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		bg = new TRGLImageView(
				new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
		game = new TamaCombine((int) scene.getCamera().getReferenceSize().getHeight());
		scene.clearScene();
		gl.clear();
		scene.addComponent(game.getField());
		scene.addComponent(bg);
		scene.addGlobalMouseListener(game);
		gl.add(game);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		scene.removeComponent(game.getField());
		scene.removeComponent(bg);
		gl.remove(game);
	}

}
