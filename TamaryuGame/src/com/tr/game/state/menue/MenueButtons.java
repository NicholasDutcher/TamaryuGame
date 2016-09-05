package com.tr.game.state.menue;

import java.util.ArrayList;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.sound.AudioMaster;

public class MenueButtons extends TRGLImageView {

	private ArrayList<TRTextButton> buttons = new ArrayList<TRTextButton>();
	private int gw = 0, gh = 0;

	private String[] options = { "start", "language", "help", "quit" };

	public MenueButtons(TRScene s) {
		super();
		this.setPosition(0, 0, -15);
		initButtons(s);
		this.setFixedPosition(FIXED_POS_CENTER);
		
		//Sound laden
		String[] audios = new String[1];
		audios[0] =  "res/sound/GitarrenLoop.wav";
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
				System.out.println("Call UP!");
				l.increasePos(-10, 0, 0);
			}
		});
		
		return  l;
	}

	private void initButtons(TRScene s) {

		for (int i = options.length - 1; i >= 0; i--) {
			//TRTextButton l = TRComponentManager.getTxtButton(options[i]);
			TRTextButton l = createButton(options[i]);
			/*l.setFont("Arial", true);
			l.setPosition(0, gh);
			gw = Math.max(gw, l.getWidth());
			// System.out.println("GW : "+gw);
			gh += l.getHeight();*/
			this.addComponent(l);
			s.addMouseListener(l);
		}

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