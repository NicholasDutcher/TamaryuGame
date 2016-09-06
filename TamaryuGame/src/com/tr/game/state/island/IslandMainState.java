package com.tr.game.state.island;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.gameobject.Decoration;
import com.tr.engine.grf.TRScene;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.game.core.states.TamaryuGameStateFactory;

public class IslandMainState extends TRAbstractGameState {
	
	private IslandMenue menue = new IslandMenue();
	
	public IslandMainState(){
		this(TamaryuGameStateFactory.ISLAND_STATE, "ISLAND_STATE");
	}

	public IslandMainState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		scene.addComponent(menue.getImage());
		gl.add(menue);
		scene.addMouseListener(menue.getButton());
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		scene.removeComponent(menue.getImage());
		gl.remove(menue);
		scene.removeMouseListener(menue.getButton());
	}

}
