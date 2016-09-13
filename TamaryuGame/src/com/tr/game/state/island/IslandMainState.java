package com.tr.game.state.island;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.objects.DragableIcon;

import demo.tama.BabyWyvern;
import demo.tama.Pear;

public class IslandMainState extends TRAbstractGameState {

	private int z = 0;
	private IslandMenue menue = new IslandMenue();
	private IslandBackground bg = new IslandBackground();
	private BabyWyvern d1 = new BabyWyvern(1);
	private DragableIcon t1 = new Pear();

	public IslandMainState() {
		this(TamaryuGameStateFactory.ISLAND_STATE, "ISLAND_STATE");
		d1.loadAnimation("lookRight");
	}

	public IslandMainState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		//AudioMaster.loadAudioFiles(audioFilePaths);
		scene.addComponent(menue.getImage());
		scene.addComponent(bg);
		scene.addComponent(d1);
		scene.addComponent(t1);
		gl.add(menue);
		//scene.addMouseListener(menue.getButton());
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl) {
		AudioMaster.clearData();
		scene.removeComponent(menue.getImage());
		scene.removeComponent(bg);
		scene.removeComponent(d1);
		scene.removeComponent(t1);
		gl.remove(menue);
		//scene.removeMouseListener(menue.getButton());
	}


}
