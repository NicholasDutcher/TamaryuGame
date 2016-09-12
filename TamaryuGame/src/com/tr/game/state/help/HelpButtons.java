package com.tr.game.state.help;

import java.util.ArrayList;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.engine.sound.AudioMaster;
import com.tr.util.LanguageTranslator;

public class HelpButtons extends TRGLImageView {
	private int gw = 0, gh = 0;

	public HelpButtons(TRScene s) {
		super();
		this.setPosition(0, 0, -15);
		initButtons(s);
		this.setFixedPosition(FIXED_POS_CENTER);
		
		//Sound laden
		String[] audios = new String[2];
		audios[0] =  "res/sound/Blob1.wav";
		audios[1] =  "res/sound/Drip1.wav";
		AudioMaster.loadAudioFiles(audios);
	}

	private TRTextButton createButton(String str) {
		final TRTextButton l = TRComponentManager.getTxtButton(str);
		l.setFont("Arial", true);
		l.setPosition(0, gh);
		gw = Math.max(gw, l.getWidth());
		gh += l.getHeight();

		l.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable() {
			@Override
			public void run() {
				
				AudioMaster.playSource(0);
				l.setRotation(0, 0, 10);
			}
		});
		
		l.addStateChangeAction(TRTextButton.MOUSE_LEAVE_ACTION, new Runnable() {
			@Override
			public void run() {
				
				AudioMaster.stopSource(0);
				l.setRotation(0, 0, 0);
			}
		});

		l.addStateChangeAction(TRTextButton.MOUSE_DOWN_ACTION, new Runnable() {
			@Override
			public void run() {
				l.increasePos(10, 0, 0);
			}
		});

		l.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {
			@Override
			public void run() {
				l.increasePos(-10, 0, 0);
			}
		});
		
		return  l;
	}


	private void initButtons(TRScene s) {

		TRTextButton backB = createButton(LanguageTranslator.getString("back"));
		backB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable(){

			@Override
			public void run() {
				AudioMaster.playSource(1);
				TRGameStateManager.setState(7);
				TRGameStateManager.reset();
			}});
		s.addComponent(backB);
		s.addMouseListener(backB);
		
		//ka was das ist
		System.out.println("GW / GH: "+gw+" / "+gh);

		for (IRenderable r : this.components) {
			// System.out.println("Set Alignment!");
			TRTextButton l = (TRTextButton) r;
			l.setSize(gw, r.getHeight());
			l.setAlignment(TRLabel.CENTER);
		}

		this.setSize(gw, gh);
	}


}
