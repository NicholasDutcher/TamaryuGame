package com.tr.game.state.menue;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.obj.state.TRAbstractGameState;

public class GameMenueState extends TRAbstractGameState{
	
	private MenueBackground bg = null;
	private MenueButtons buttons = null;
	
	public GameMenueState(){
		this(0, "Game_Menue");
	}

	public GameMenueState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		initComponents(scene);
		scene.clearScene();
		gl.clear();
		addComponents(scene);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		removeComponents(scene);
	}
	
	private void initComponents(TRScene scene){
		bg = new MenueBackground();
		
	}
	
	private void addComponents(TRScene scene){
		scene.addComponent(bg);
		buttons = new MenueButtons(scene);
		scene.addComponent(buttons);
	}
	
	private void removeComponents(TRScene scene){
		scene.removeComponent(buttons);
		scene.removeComponent(bg);
	}

}
