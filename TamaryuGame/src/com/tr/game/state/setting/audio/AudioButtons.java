package com.tr.game.state.setting.audio;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.util.LanguageTranslator;

public class AudioButtons extends TRGLImageView {
	private int gw = 0, gh = 0;

	public AudioButtons(TRScene s) {
		super();
		this.setPosition(0, 0, -15);
		initButtons(s);
		this.setFixedPosition(FIXED_POS_CENTER);
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
				l.setColor(new Color(255f / 255f, 153f / 255f, 18f / 255f, 0f));
				AudioMaster.playSource(0);
			}
		});

		l.addStateChangeAction(TRTextButton.MOUSE_LEAVE_ACTION, new Runnable() {
			@Override
			public void run() {
				l.setColor(new Color(255f / 255f, 255 / 255f, 255 / 255f, 0f));
				AudioMaster.stopSource(0);
			}
		});

		l.addStateChangeAction(TRTextButton.MOUSE_DOWN_ACTION, new Runnable() {
			@Override
			public void run() {
				l.setColor(new Color(205f / 255f, 102f / 255f, 9f / 255f, 0f));
			}
		});

		l.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {
			@Override
			public void run() {
				l.increasePos(-10, 0, 0);
			}
		});

		return l;
	}

	private void initButtons(TRScene s) {
		// backButton
		TRTextButton backB = createButton(LanguageTranslator.getString("back"));
		backB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {

			@Override
			public void run() {
				AudioMaster.playSource(1);
				TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_STATE);
				// TRGameStateManager.reset();
			}
		});
		this.addComponent(backB);
		gh += 10;
		
		//mute
		TRTextButton muteB = createButton(LanguageTranslator.getString("mutesound"));
		muteB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				if (AudioMaster.getVolume() != 0.0f)
				{
					AudioMaster.setVolume(0.0f);
				}
			}
		});
		this.addComponent(muteB);
		
		//25%
		TRTextButton quaterVolumeB = createButton("Audio Volume 25%");
		quaterVolumeB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				if (AudioMaster.getVolume() != 0.25f)
				{
					AudioMaster.setVolume(0.25f);
				}
			}
		});
		this.addComponent(quaterVolumeB);

		TRTextButton halfVolumeB = createButton("Audio Volume 50%");
		halfVolumeB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				if (AudioMaster.getVolume() != 0.5f)
				{
					AudioMaster.setVolume(0.5f);
				}
			}
		});
		this.addComponent(halfVolumeB);
		
		TRTextButton gibbousVolumeB = createButton("Audio Volume 75%");
		gibbousVolumeB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				if (AudioMaster.getVolume() != 0.75f)
				{
					AudioMaster.setVolume(0.75f);
				}
			}
		});
		this.addComponent(gibbousVolumeB);
		
		TRTextButton fullVolumeB = createButton("Audio Volume 100%");
		fullVolumeB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				if (AudioMaster.getVolume() != 1.0f)
				{
					AudioMaster.setVolume(1.0f);
				}
			}
		});
		this.addComponent(fullVolumeB);

		for (IRenderable r : this.inComponents) {
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
