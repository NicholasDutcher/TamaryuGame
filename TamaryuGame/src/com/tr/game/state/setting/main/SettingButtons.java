package com.tr.game.state.setting.main;

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

public class SettingButtons extends TRGLImageView
{
	private int gw = 0, gh = 0;

	public SettingButtons(TRScene s)
	{
		super();
		this.setPosition(0, 0, -15);
		initButtons(s);
		this.setFixedPosition(FIXED_POS_CENTER);
	}

	private TRTextButton createButton(String str)
	{
		final TRTextButton l = TRComponentManager.getTxtButton(str);
		l.setFont("Arial", true);
		l.setPosition(0, gh);
		gw = Math.max(gw, l.getWidth());
		gh += l.getHeight();

		l.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				l.setColor(new Color(255f / 255f, 153f / 255f, 18f / 255f, 0f));
				if(!AudioMaster.isPlaying(0))
				{
					AudioMaster.playSource(0);
				}
			}
		});
		l.addStateChangeAction(TRTextButton.MOUSE_LEAVE_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				l.setColor(new Color(255f / 255f, 255 / 255f, 255 / 255f, 0f));
				
			}
		});
		l.addStateChangeAction(TRTextButton.MOUSE_DOWN_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				l.setColor(new Color(205f / 255f, 102f / 255f, 9f / 255f, 0f));
			}
		});
		l.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				l.increasePos(-10, 0, 0);
			}
		});

		return l;
	}

	private void initButtons(TRScene s)
	{
		// backButton
		final TRTextButton backB = createButton(LanguageTranslator.getString("back"));
		backB.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				backB.setColor(new Color(255f / 255f, 153f / 255f, 18f / 255f, 0f));
				if(AudioMaster.isPlaying(0))
				{
					AudioMaster.stopSource(0);
				}
			}
		});
		backB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				AudioMaster.stopSource(0);
				TRGameStateManager.setState(TamaryuGameStateFactory.MENU_STATE);
			}
		});
		this.addComponent(backB);
		gh += 10;

		TRTextButton langB = createButton(LanguageTranslator.getString("language"));
		langB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run(){
				TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_LANG_STATE);
			}
		});
		this.addComponent(langB);

		TRTextButton audioB = createButton("Audio");
		audioB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_AUDIO_STATE);
			}
		});
		this.addComponent(audioB);
		
		for (IRenderable r : this.inComponents)
		{
			TRTextButton l = (TRTextButton) r;
			l.setSize(gw, r.getHeight());
			l.setAlignment(TRLabel.CENTER);
		}

		this.setSize(gw, gh);
	}
}
