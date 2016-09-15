package com.tr.game.state.login;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.grf.gl.TRGLRenderable;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.objects.TamaryuLogo;

public class LoginState extends TRAbstractGameState {
	private TRGLImageView bg;
	private LoginInputs userinterface;
	private TamaryuLogo logo = new TamaryuLogo();

	public LoginState() {
		this(TamaryuGameStateFactory.LOGIN_STATE, "Login");
	}

	public LoginState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		initComponents(scene);
		scene.clearScene();
		gl.clear();
		addComponents(scene, gl);
		scene.addGlobalKeyListener(userinterface);
		
		// Sound laden
		String[] audios = new String[2];
		audios[0] = "res/sound/Blob1.wav";
		audios[1] = "res/sound/Drip1.wav";
		AudioMaster.loadAudioFiles(audios);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		scene.removeGlobalKeyListener(userinterface);
		AudioMaster.clearData();
		removeComponents(scene, gl);
	}

	private void initComponents(TRScene scene) {
		bg = new TRGLImageView(
				new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
		bg.setZ(0);
	}

	private void addComponents(TRScene scene, TRGameLooper gl) {
		scene.addComponent(bg);
		userinterface = new LoginInputs(scene);
		userinterface.getContainer().setZ(2);
		logo.setZ(1);
		logo.setFixedPosition(TRGLRenderable.FIXED_POS_TOP);
		scene.addComponent(userinterface.getContainer());
		gl.add(userinterface);
		scene.addComponent(logo);
	}

	private void removeComponents(TRScene scene, TRGameLooper gl) {
		scene.removeComponent(userinterface.getContainer());
		gl.remove(userinterface);
		scene.removeComponent(bg);
		scene.removeComponent(logo);
	}

}
