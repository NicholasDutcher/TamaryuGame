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
				TRGameStateManager.setState(TamaryuGameStateFactory.ISLAND_STATE);
			}
		});
		this.addComponent(backB);
		gh += 10;

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

		TRTextButton quaterVolumeB = createButton("Audio_Volume_25%");
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
		
		TRTextButton halfVolumeB = createButton("Audio_Volume_50%");
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
		
		TRTextButton gibbousVolumeB = createButton("Audio_Volume_75%");
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
		
		TRTextButton fullVolumeB = createButton("Audio_Volume_100%");
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
		
		// setGermanButton
				final TRTextButton deB = createButton(LanguageTranslator.getString("german"));
				deB.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable()
				{
					@Override
					public void run()
					{
						deB.setColor(new Color(255f / 255f, 153f / 255f, 18f / 255f, 0f));
						if(AudioMaster.isPlaying(0))
						{
							AudioMaster.stopSource(0);
						}
					}
				});
				deB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {

					@Override
					public void run() {

						if (!LanguageTranslator.getCurrentLanguage().contains("de")) {
							LanguageTranslator.changeLanguage("de");
							TRGameStateManager.reset();
						}
					}
				});
				this.addComponent(deB);

				// setEnglishButton
				final TRTextButton enB = createButton(LanguageTranslator.getString("english"));
				enB.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable()
				{
					@Override
					public void run()
					{
						enB.setColor(new Color(255f / 255f, 153f / 255f, 18f / 255f, 0f));
						if(AudioMaster.isPlaying(0))
						{
							AudioMaster.stopSource(0);
						}
					}
				});
				enB.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {

					@Override
					public void run() { 

						if (!LanguageTranslator.getCurrentLanguage().contains("en")) {
							LanguageTranslator.changeLanguage("en");
							TRGameStateManager.reset();
						}
					}
				});
				this.addComponent(enB);
		
		for (IRenderable r : this.inComponents)
		{
			TRTextButton l = (TRTextButton) r;
			l.setSize(gw, r.getHeight());
			l.setAlignment(TRLabel.CENTER);
		}

		this.setSize(gw, gh);
	}
}
