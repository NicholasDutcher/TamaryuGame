package com.tr.game.state.island;

import com.tr.engine.core.TRGameLooper;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.input.ITRGlobalMouseListener;
import com.tr.engine.input.TRGlobalMouseEvent;
import com.tr.engine.obj.state.TRAbstractGameState;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.objects.DragableIcon;
import com.tr.game.objects.Dragon;
import com.tr.game.objects.dragons.DragonAnimation;
import com.tr.game.objects.dragons.Wyvern;

import demo.tama.Pear;

public class IslandMainState extends TRAbstractGameState implements ITRGlobalMouseListener {

	// private int z = 0;
	private IslandMenue menue = new IslandMenue();
	private IslandBackground bg = new IslandBackground();
	// private Wyvern d1 = new Wyvern();
	private DragableIcon t1 = new Pear();
	private IslandMenueManager manager;

	private Wyvern[] dragons = new Wyvern[3];
	private Wyvern selD = null;

	public IslandMainState() {
		this(TamaryuGameStateFactory.ISLAND_STATE, "ISLAND_STATE");
		manager = new IslandMenueManager(menue);

		for (int i = 0; i < dragons.length; i++) {
			Wyvern w = new Wyvern();
			w.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1));
			w.setEyeColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 1));
			dragons[i] = w;
		}
		// d1.setColor(new Color(50f, 15f, 180f, 1));
		// d1.setEyeColor(new Color(30f, 255f, 100f, 1));
	}

	public IslandMainState(int id, String name) {
		super(id, name);
	}

	@Override
	public void load(TRScene scene, TRGameLooper gl) {
		// AudioMaster.loadAudioFiles(audioFilePaths);
		scene.addComponent(menue.getImage());
		scene.addComponent(bg);
		// scene.addComponent(d1.getImage());
		scene.addComponent(t1);
		scene.addGlobalMouseListener(this);
		gl.add(menue);
		// gl.add(d1);

		for (int i = 0; i < dragons.length; i++) {
			scene.addComponent(dragons[i].getImage());
			gl.add(dragons[i]);
		}
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
		scene.removeGlobalMouseListener(this);
		scene.removeComponent(menue.getImage());
		scene.removeComponent(bg);
		// scene.removeComponent(d1.getImage());
		scene.removeComponent(t1);
		gl.remove(menue);
		// gl.remove(d1);

		for (int i = 0; i < dragons.length; i++) {
			scene.removeComponent(dragons[i].getImage());
			gl.remove(dragons[i]);
		}
		// scene.removeMouseListener(menue.getButton());
	}

	@Override
	public void mouseEnter(TRGlobalMouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseLeave(TRGlobalMouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseRelease(TRGlobalMouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePress(TRGlobalMouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(TRGlobalMouseEvent tre) {
		if (tre.src == null) {
			if (selD != null) {
				selD.setIdleMove(true);
				selD = null;
			}
		} else {
			IRenderable r = tre.src;
			while (r.getParent() != null) {
				r = r.getParent();
			}

			if (r instanceof DragonAnimation) {
				if (selD == null || !selD.equals(r)) {
					if(selD != null)
						selD.setIdleMove(true);
					selD = (Wyvern) ((DragonAnimation) r).getRef();
					selD.setIdleMove(false);
				}
			}
		}
	}

	@Override
	public void mouseMoved(TRGlobalMouseEvent tre) {
		if (tre.src == null) {
			if (selD != null) {
				selD.setIdleMove(true);
				selD = null;
			}
		} else {
			IRenderable r = tre.src;
			while (r.getParent() != null) {
				r = r.getParent();
			}

			if (r instanceof DragonAnimation) {
				if (selD == null || !selD.equals(r)) {
					if(selD != null)
						selD.setIdleMove(true);
					selD = (Wyvern) ((DragonAnimation) r).getRef();
					selD.setIdleMove(false);
				}
			}
		}
	}

	@Override
	public void mouseClicked(TRGlobalMouseEvent tre) {
		if (tre.src == null) {
			return;
		} else {
			IRenderable r = tre.src;
			while (r.getParent() != null) {
				r = r.getParent();
			}

			if (r instanceof DragonAnimation) {
					Wyvern d = (Wyvern) ((DragonAnimation) r).getRef();
					manager.setStatsTarget(d);
			}
		}
	}

}
