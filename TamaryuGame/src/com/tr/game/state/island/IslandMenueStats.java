package com.tr.game.state.island;

import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.game.objects.Dragon;
import com.tr.game.objects.TamaProgressStat;
import com.tr.game.objects.TamaTextStat;
import com.tr.game.objects.TamaryuButton;
import com.tr.util.LanguageTranslator;

public class IslandMenueStats extends TRGLImageView {
	
	private TamaryuButton backB = null;
	
	private IslandMenueManager manager = null;
	private TamaTextStat atkStat = new TamaTextStat("null", 0);
	private TamaTextStat defStat = new TamaTextStat("null", 0);
	private TamaProgressStat hpStat = new TamaProgressStat("null", 0, 10, 5);
	private TamaProgressStat hungerStat = new TamaProgressStat("null", 0, 10, 5);
	private TamaProgressStat moodStat = new TamaProgressStat("null", 0, 10, 5);
	
	private Dragon dragon = null;
	
	private boolean showen = false;
	private long lastUpdate = 0;

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
		if(d != null)
		
		atkStat.setName("Atk.");
		atkStat.setValue(d.getAttack());
		
		defStat.setName("Def.");
		defStat.setValue(d.getDefense());
		
		hpStat.setName("HP");
		hpStat.setMinValue(0);
		hpStat.setMaxValue(d.getHpMax());
		hpStat.setValue(d.getHp());
		
		hungerStat.setName("Hunger");
		hungerStat.setMinValue(0);
		hungerStat.setMaxValue(100);
		hungerStat.setValue(d.getHunger());
		
		moodStat.setName("Mood");
		moodStat.setMinValue(0);
		moodStat.setMaxValue(100);
		moodStat.setValue(d.getMood());
		
		atkStat.setPosition(80, -40, this.getPosition().z+10);
		defStat.setPosition(80, -80, this.getPosition().z+10);
		hpStat.setPosition(80, -140, this.getPosition().z+10);
		hungerStat.setPosition(80, -200, this.getPosition().z+10);
		moodStat.setPosition(80, -260, this.getPosition().z+10);
		
		this.addComponent(atkStat);
		this.addComponent(defStat);
		this.addComponent(hpStat);
		this.addComponent(hungerStat);
		this.addComponent(moodStat);
		
		dragon =d;
		showen = true;
	}
	
	public void hide(){
		this.removeComponent(atkStat);
		this.removeComponent(defStat);
		this.removeComponent(hpStat);
		this.removeComponent(hungerStat);
		this.removeComponent(moodStat);
		
		dragon = null;
		showen = false;
	}
	
	public void render(TRRenderContext rc){
		super.render(rc);
		
		if(showen && (System.currentTimeMillis()-lastUpdate) > 500){
			lastUpdate = System.currentTimeMillis();
			if(dragon != null){
				atkStat.setValue(dragon.getAttack());
				defStat.setValue(dragon.getDefense());
				hpStat.setValue(dragon.getHp());
				hungerStat.setValue(dragon.getHunger());
				moodStat.setValue(dragon.getMood());
			}
		}
	}
	
	
	
	public void setPosition(float x, float y, float z){
		super.setPosition(x, y, z);
		backB.setZ(z+1);
		atkStat.setZ(z+1);
		defStat.setZ(z+1);
	}
}
