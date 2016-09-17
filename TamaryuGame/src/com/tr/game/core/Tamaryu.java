package com.tr.game.core;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.TRGameWindow;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLRenderContext;
import com.tr.engine.input.TRDragAndDropManager;
import com.tr.engine.obj.state.TRGameStateFactory;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;

import demo.tama.TamaAnimation1;

public class Tamaryu {
	// graphics
	protected TRRenderContext rc;
	protected TRGameWindow gw;
	protected TRScene scene;

	// game manager
	protected TRGameLooper looper = new TRGameLooper();
	public static TRDragAndDropManager dndm = new TRDragAndDropManager();
	protected TRGameStateFactory gsf = new TamaryuGameStateFactory();

	public Tamaryu() {
		// init graphics
		initGraphics();

		// init sound
		initAudio();

		// init looper
		initLooper();

		// init DnD
		initDnD();

		// init gamestate manager
		initGSM();

		// load first gamestate
		TRGameStateManager.setState(TamaryuGameStateFactory.LOGIN_STATE);

		// scene.addComponent(new MenueBackground());
		// scene.addComponent(new MenueButtons(scene));
		// scene.addComponent(new TamaAnimation1());

	}

	private void initAudio() {
		AudioMaster.initialize();
	}

	private void initGSM() {
		TRGameStateManager.init(scene, looper);
		TRGameStateManager.setFactory(gsf);
	}

	private void initGraphics() {
		rc = new TRGLRenderContext(1024, 740);
		rc.setDebug(true);
		rc.init();
		rc.setGRP(true);
		//rc.addRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_8BIT_COLOR, 1));
		rc.addOnClose(new Runnable() {

			@Override
			public void run() {
				destroy();
			}
		});

		gw = rc.createWindow();
		gw.setFullscreen(true);
		scene = rc.getScene();
		gw.setTitel("Tamaryu v0.1");
		gw.start();
	}

	private void initLooper() {
		looper.setTargetUPS(30);
		looper.start();
	}

	private void initDnD() {
		/*
		 * Click to start drag Allow to drop anywhere If dropping fails, return
		 * object to drag start coordinates Allow only one single dragged object
		 * at a time; Enable dnd manager
		 * 
		 * Add manager to the scene
		 */
		dndm.setDragOnOver(false);
		dndm.setDropAreaOnly(true);
		dndm.setReturnOnDropFail(true);
		dndm.setSingleDrag(true);
		dndm.setEnabled(true);
		scene.setDnDManager(dndm);
	}

	private void destroy() {
		AudioMaster.killAllData();
	}

	public static void main(String[] args) {
		new Tamaryu();
	}

}
