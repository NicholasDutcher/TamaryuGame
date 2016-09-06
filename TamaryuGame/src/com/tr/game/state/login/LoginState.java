package com.tr.game.state.login;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.obj.state.TRAbstractGameState;

public class LoginState extends TRAbstractGameState
{
	private TRGLImageView bg;
	private LoginInputs userinterface;
	
	public LoginState()
	{
		this(0, "Login");
	}

	public LoginState(int id, String name)
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
	}

	@Override
	public void unload(TRScene scene, TRGameLooper gl)
	{
		removeComponents(scene);
	}
	
	private void initComponents(TRScene scene)
	{
		bg = new TRGLImageView(new TRImage("gb_evening_1", "savanna_bg_evening", "png", "/img", 0, 0, 0, 1280, 1024, 1280, 1024));
		bg.setFixedPosition(TRGLImageView.FIXED_POS_CENTER);
	}
	
	private void addComponents(TRScene scene)
	{
		scene.addComponent(bg);
		userinterface = new LoginInputs(scene);
		scene.addComponent(userinterface.getContainer());
	}
	
	private void removeComponents(TRScene scene)
	{
		scene.removeComponent(userinterface.getContainer());
		scene.removeComponent(bg);
	}

}
