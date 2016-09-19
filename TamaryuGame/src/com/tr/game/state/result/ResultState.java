package com.tr.game.state.result;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;

public class ResultState extends TRAbstractGameState 
{
	private TRGLImageView bg;
	private ResultLabels labels;
	
	public ResultState()
	{
		this(TamaryuGameStateFactory.RESULT_STATE, "RESULT_STATE");
	}
	
	public ResultState(int id, String name)
	{
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl)
	{
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
	public void unload(TRScene scene, TRGameLooper gl)
	{
		AudioMaster.clearData();
		removeComponents(scene);
	}
	
	private void initComponents(TRScene scene)
	{
		this.bg = new TRGLImageView(new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		this.bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
	}
	
	private void addComponents(TRScene scene)
	{
		scene.addComponent(this.bg);
		
		this.labels = new ResultLabels(scene);
		labels.setZ(2);
		scene.addComponent(this.labels);
	}
	
	private void removeComponents(TRScene scene)
	{
		scene.removeComponent(this.labels);
		scene.removeComponent(this.bg);
	}
}
