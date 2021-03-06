package com.tr.game.state.setting.lang;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.objects.TamaryuLogo;

public class SettingsLangState extends TRAbstractGameState {

	private TRGLImageView bg = null;
	private LangButtons buttons = null;
	private TamaryuLogo logo = new TamaryuLogo();

	public SettingsLangState() {
		this(TamaryuGameStateFactory.SETTING_LANG_STATE, "SETTINGS_LANG");
	}

	public SettingsLangState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		initComponents(scene);
		scene.clearScene();
		gl.clear();
		addComponents(scene);
		scene.addComponent(logo);
		
		//Sound laden
		String[] audios = new String[2];
		audios[0] = "res/sound/Blob1.wav";
		audios[1] = "res/sound/Drip1.wav";
		AudioMaster.loadAudioFiles(audios);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		AudioMaster.clearData();
		removeComponents(scene);
		scene.removeComponent(logo);
	}

	private void initComponents(TRScene scene) {
		bg = new TRGLImageView(
				new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
	}

	private void addComponents(TRScene scene) {
		scene.addComponent(bg);
		buttons = new LangButtons(scene);
		scene.addComponent(buttons);
	}

	private void removeComponents(TRScene scene) {
		scene.removeComponent(buttons);
		scene.removeComponent(bg);
	}

}
