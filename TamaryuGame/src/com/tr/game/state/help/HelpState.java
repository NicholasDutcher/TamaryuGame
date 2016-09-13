package com.tr.game.state.help;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.state.help.HelpButtons;

public class HelpState extends TRAbstractGameState {

	private TRGLImageView bg = null;
	private HelpButtons buttons = null;

	public HelpState() {
		this(TamaryuGameStateFactory.HELP_STATE, "HELP");
	}

	public HelpState(int id, String name) {
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
		AudioMaster.clearData();
		removeComponents(scene);
	}

	private void initComponents(TRScene scene) {
		bg = new TRGLImageView(
				new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
	}

	private void addComponents(TRScene scene) {
		scene.addComponent(bg);
		buttons = new HelpButtons(scene);
		scene.addComponent(buttons);
	}

	private void removeComponents(TRScene scene) {
		scene.removeComponent(buttons);
		scene.removeComponent(bg);
	}

}
