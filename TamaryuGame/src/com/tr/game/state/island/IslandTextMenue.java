package com.tr.game.state.island;

import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.obj.state.TRGameStateManager;
import com.tr.game.core.states.TamaryuGameStateFactory;
import com.tr.game.objects.TamaryuButton;
import com.tr.util.LanguageTranslator;

public class IslandTextMenue extends TRGLImageView {
	
	private TamaryuButton exitB = null;
	private TamaryuButton gameB = null;
	private TamaryuButton settingsB = null;
	private TamaryuButton inventoryB = null;
	private TamaryuButton statsB = null;
	
	private IslandMenueManager manager = null;

	public IslandTextMenue(IslandMenueManager mm) {
		super();
		this.manager = mm;
		this.setSize(300, 400);
		
		// exit button
		exitB = new TamaryuButton(LanguageTranslator.getString("quit"));
		exitB.addClickAction(new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		});
		exitB.setAlignment(TRLabel.CENTER);
		exitB.setPosition(80, -334);
		exitB.setSize(170, 38);
		exitB.setZ(this.getPosition().z + 1);
		this.addComponent(exitB);

		// inventory, stats, settings, minigame
		gameB = new TamaryuButton(LanguageTranslator.getString("minigame"));
		gameB.addClickAction(new Runnable() {
			@Override
			public void run() {
				System.exit(0);
			}
		});
		gameB.setPosition(80, -164);
		gameB.setAlignment(TRLabel.CENTER);
		gameB.setSize(170, 38);
		gameB.setZ(this.getPosition().z + 1);
		this.addComponent(gameB);

		// inventory, stats, settings, minigame
		settingsB = new TamaryuButton(LanguageTranslator.getString("settings"));
		settingsB.addClickAction(new Runnable() {
			@Override
			public void run() {
				TRGameStateManager.setState(TamaryuGameStateFactory.SETTING_STATE);
			}
		});
		settingsB.setPosition(80, -126);
		settingsB.setAlignment(TRLabel.CENTER);
		settingsB.setSize(170, 38);
		settingsB.setZ(this.getPosition().z + 1);
		this.addComponent(settingsB);

		// inventory, stats, settings, minigame
		inventoryB = new TamaryuButton(LanguageTranslator.getString("inventory"));
		inventoryB.addClickAction(new Runnable() {
			@Override
			public void run() {
				manager.loadState(IslandMenueManager.INVENTORY);
			}
		});
		inventoryB.setPosition(80, -88);
		inventoryB.setAlignment(TRLabel.CENTER);
		inventoryB.setSize(170, 38);
		inventoryB.setZ(this.getPosition().z + 1);
		this.addComponent(inventoryB);

		// inventory, stats, settings, minigame
		statsB = new TamaryuButton(LanguageTranslator.getString("stats"));
		statsB.addClickAction(new Runnable() {
			@Override
			public void run() {
				manager.loadState(IslandMenueManager.STATS);
			}
		});
		statsB.setPosition(80, -50);
		statsB.setAlignment(TRLabel.CENTER);
		statsB.setSize(170, 38);
		statsB.setZ(this.getPosition().z + 1);
		this.addComponent(statsB);
	}
	
	public void setPosition(float x, float y, float z){
		super.setPosition(x, y, z);
		exitB.setZ(z+1);
		gameB.setZ(z+1);
		settingsB.setZ(z+1);
		inventoryB.setZ(z+1);
		statsB.setZ(z+1);
	}
}
