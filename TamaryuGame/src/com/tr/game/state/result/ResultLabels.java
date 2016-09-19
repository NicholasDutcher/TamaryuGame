package com.tr.game.state.result;

import com.tr.engine.grf.Color;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRScene;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.inventory.InventorySystem;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.engine.sound.AudioMaster;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.util.LanguageTranslator;
import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;

class ResultLabels extends TRGLImageView
{
	private TRLabel resultLabel;
	
	private TRLabel dragonNameLabel;
	private TRLabel scoreLabel;
	private TRLabel lootLabel;
	
	private TRTextButton okButton;
			
	public ResultLabels(TRScene s)
	{	
		this.setButtons();
		this.setLabel();
		
		this.setSize(510, 314);
		this.setFixedPosition(IRenderable.FIXED_POS_CENTER);
		
		this.dragonNameLabel = TRComponentManager.getLabel(LanguageTranslator.getString("result"));
	}
	
	private void setButtons()
	{
		this.okButton = this.createButton("ok");
		
		this.okButton.setSize(510, this.okButton.getHeight());
		this.okButton.setAlignment(TRLabel.CENTER);
		this.okButton.setPosition(0, 25, 4);
		
		this.addComponent(this.okButton);
	}
	
	private TRTextButton createButton(String str)
	{
		final TRTextButton button = TRComponentManager.getTxtButton(str);
		button.setFont("Arial", true);
		button.setPosition(0, 0);

		button.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				button.setColor(new Color(255f / 255f, 153f / 255f, 18f / 255f, 0f));
				AudioMaster.playSource(0);
			}
		});
		button.addStateChangeAction(TRTextButton.MOUSE_LEAVE_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				button.setColor(new Color(255f / 255f, 255 / 255f, 255 / 255f, 0f));
				AudioMaster.stopSource(0);
			}
		});
		button.addStateChangeAction(TRTextButton.MOUSE_DOWN_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				button.setColor(new Color(205f / 255f, 102f / 255f, 9f / 255f, 0f));
			}
		});
		button.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable()
		{
			@Override
			public void run()
			{
				TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_LANG_STATE);
			}
		});

		return button;
	}
	
	private void setLabel()
	{
		int height = okButton.getHeight()*3;
		String temp =  InventorySystem.getNewestItem().getName();
		
		this.lootLabel = createLabel(LanguageTranslator.getString("loot") + ": " + temp, 0, height);
		this.addComponent(this.lootLabel);
		height += this.lootLabel.getHeight()*2;
		
		this.scoreLabel = createLabel(LanguageTranslator.getString("score"), 0, height);
		this.addComponent(this.scoreLabel);
		height += this.scoreLabel.getHeight()*2;
		
		this.dragonNameLabel = createLabel(LanguageTranslator.getString("dragon"), 0, height);
		this.addComponent(this.dragonNameLabel);
		height += this.dragonNameLabel.getHeight()*2;
		
		this.resultLabel = createLabel(LanguageTranslator.getString("result"), 150, height);
		this.resultLabel.setAlignment(FIXED_POS_CENTER);
		this.addComponent(this.resultLabel);
	}
	
	private TRLabel createLabel(String txt, int x, int y)
	{
		TRLabel tempLabel = TRComponentManager.getLabel();
		tempLabel.setFont("Arial", true);
		tempLabel.setColor(new Color(255,255,255,0));
		tempLabel.setText(txt);
		tempLabel.setSize(250, 38);
		tempLabel.setAlignment(TRLabel.CENTER);
		tempLabel.setPosition(x, y, 8);
		
		return tempLabel;
	}
}
