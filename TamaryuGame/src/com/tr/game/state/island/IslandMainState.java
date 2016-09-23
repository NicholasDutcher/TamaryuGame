package com.tr.game.state.island;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.TRScene;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.objects.DragableIcon;
import com.tr.game.objects.dragons.Wyvern;

import demo.tama.Pear;

public class IslandMainState extends TRAbstractGameState {

	private int z = 0;
	private IslandMenue menue = new IslandMenue();
	private IslandBackground bg = new IslandBackground();
	private Wyvern d1 = new Wyvern();
	private DragableIcon t1 = new Pear();
	private IslandMenueManager manager;

	public IslandMainState() {
		this(TamaryuGameStateFactory.ISLAND_STATE, "ISLAND_STATE");
		manager = new IslandMenueManager(menue);
		d1.setColor(new Color(50f, 15f, 180f, 1));
		d1.setEyeColor(new Color(30f, 255f, 100f, 1));
	}

	public IslandMainState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		// AudioMaster.loadAudioFiles(audioFilePaths);
		scene.addComponent(menue.getImage());
		scene.addComponent(bg);
		scene.addComponent(d1.getImage());
		scene.addComponent(t1);
		gl.add(menue);
		gl.add(d1);
		// scene.addMouseListener(menue.getButton());

		// Sound laden
		String[] audios = new String[2];
		audios[0] = "res/sound/Blob1.wav";
		audios[1] = "res/sound/Drip1.wav";
		AudioMaster.loadAudioFiles(audios);
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		AudioMaster.clearData();
		scene.removeComponent(menue.getImage());
		scene.removeComponent(bg);
		scene.removeComponent(d1.getImage());
		scene.removeComponent(t1);
		gl.remove(menue);
		gl.remove(d1);
		// scene.removeMouseListener(menue.getButton());
	}

}
