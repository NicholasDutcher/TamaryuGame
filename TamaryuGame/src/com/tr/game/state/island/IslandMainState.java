package com.tr.game.state.island;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.game.core.states.TamaryuGameStateFactory;

import demo.tama.BabyWyvern;

public class IslandMainState extends TRAbstractGameState {

	private int z = 0;
	private IslandMenue menue = new IslandMenue();
	private IslandBackground bg = new IslandBackground();
	private BabyWyvern d1 = new BabyWyvern(1);

	public IslandMainState() {
		this(TamaryuGameStateFactory.ISLAND_STATE, "ISLAND_STATE");
	}

	public IslandMainState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		scene.addComponent(menue.getImage());
		scene.addComponent(bg);
		scene.addComponent(d1);
		gl.add(menue);
		//scene.addMouseListener(menue.getButton());
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		scene.removeComponent(menue.getImage());
		scene.removeComponent(bg);
		scene.removeComponent(d1);
		gl.remove(menue);
		//scene.removeMouseListener(menue.getButton());
	}


}
