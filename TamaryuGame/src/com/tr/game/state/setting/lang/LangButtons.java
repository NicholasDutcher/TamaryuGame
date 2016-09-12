package com.tr.game.state.setting.lang;

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

public class LangButtons extends TRGLImageView {
	private int gw = 0, gh = 0;

	public LangButtons(TRScene s) {
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

//setGermanButton
		TRTextButton deB = createButton(LanguageTranslator.getString("german"));
		deB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable(){

			@Override
			public void run() {
				AudioMaster.playSource(1);
				LanguageTranslator.changeLanguage("de");	
				TRGameStateManager.reset();
			}});
		this.addComponent(deB);
		s.addMouseListener(deB);

//setEnglishButton
		TRTextButton enB = createButton(LanguageTranslator.getString("english"));
		enB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable(){

			@Override
			public void run() {
				AudioMaster.playSource(1);
				LanguageTranslator.changeLanguage("en");	
				TRGameStateManager.reset();
			}});
		this.addComponent(enB);
		s.addMouseListener(enB);

//backButton
		TRTextButton backB = createButton(LanguageTranslator.getString("back"));
		backB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable(){

			@Override
			public void run() {
				AudioMaster.playSource(1);
				TRGameStateManager.setState(7);
				TRGameStateManager.reset();
			}});
		this.addComponent(backB);
		s.addMouseListener(backB);
		
		
		System.out.println("GW / GH: "+gw+" / "+gh);

		for (IRenderable r : this.components) {
			// System.out.println("Set Alignment!");
			TRTextButton l = (TRTextButton) r;
			l.setSize(gw, r.getHeight());
			l.setAlignment(TRLabel.CENTER);
		}

		this.setSize(gw, gh);
	}

	/*
	 * public void resize(TRRenderContext rc, int w, int h){
	 * this.setPosition((w-this.width)/2, (h-this.height)/2,
	 * this.getPosition().z);
	 * //System.out.println("Position: "+this.getPosition()); }
	 */
}
