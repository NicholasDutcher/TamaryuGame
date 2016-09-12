package com.tr.game.state.menue;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;

public class GameMenueState extends TRAbstractGameState {

	private TRGLImageView bg = null;
	private MenueButtons buttons = null;

	public GameMenueState() {
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
		
		// Sound laden
		String[] audios = new String[2];
		audios[0] = "res/sound/Blob1.wav";
		audios[1] = "res/sound/Drip1.wav";
		AudioMaster.loadAudioFiles(audios);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		AudioMaster.killAllData();
		removeComponents(scene);
	}

	private void initComponents(TRScene scene) {
		bg = new TRGLImageView(
				new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
	}

	private void addComponents(TRScene scene) {
		scene.addComponent(bg);
		buttons = new MenueButtons(scene);
		scene.addComponent(buttons);
	}

	private void removeComponents(TRScene scene) {
		//buttons.removeAll();
		scene.removeComponent(buttons);
		scene.removeComponent(bg);
	}

}
