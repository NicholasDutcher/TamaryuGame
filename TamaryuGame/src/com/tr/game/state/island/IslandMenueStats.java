package com.tr.game.state.island;

import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.game.objects.Dragon;
import com.tr.game.objects.TamaTextStat;
import com.tr.game.objects.TamaryuButton;
import com.tr.util.LanguageTranslator;

public class IslandMenueStats extends TRGLImageView {
	
	private TamaryuButton backB = null;
	
	private IslandMenueManager manager = null;
	private TamaTextStat atkStat = new TamaTextStat("null", 0);
	private TamaTextStat defStat = new TamaTextStat("null", 0);

	public IslandMenueStats(IslandMenueManager mm) {
		super();
		this.manager = mm;
		this.setSize(300, 400);
		
		// back button
		backB = new TamaryuButton(LanguageTranslator.getString("back"));
		backB.addClickAction(new Runnable() {
			@Override
			public void run() {
				manager.loadState(IslandMenueManager.MENUE);
			}
		});
		backB.setAlignment(TRLabel.CENTER);
		backB.setPosition(80, -334);
		backB.setSize(170, 38);
		backB.setZ(this.getPosition().z + 1);
		this.addComponent(backB);
		
		show(new Dragon());
	}
	
	public void show(Dragon d){
		atkStat.setName("Atk.");
		atkStat.setValue(d.getAttack());
		
		defStat.setName("Def.");
		defStat.setValue(d.getDefense());
		
		atkStat.setPosition(80, -40, this.getPosition().z+10);
		defStat.setPosition(80, -80, this.getPosition().z+10);
		
		this.addComponent(atkStat);
		this.addComponent(defStat);
	}
	
	public void hide(){
		this.removeComponent(atkStat);
		this.removeComponent(defStat);
	}
	
	public void setPosition(float x, float y, float z){
		super.setPosition(x, y, z);
		backB.setZ(z+1);
		atkStat.setZ(z+1);
		defStat.setZ(z+1);
	}
}
