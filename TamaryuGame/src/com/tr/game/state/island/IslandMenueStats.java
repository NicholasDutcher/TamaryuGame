package com.tr.game.state.island;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.Color;
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
	private TRLabel nameL = TRComponentManager.getLabel("null");
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
		
		nameL.setText(d.getName());
		nameL.setColor(Color.ORANGE);
		
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
		
		nameL.setPosition(80, -30, this.getPosition().z+2);
		atkStat.setPosition(80, -70, this.getPosition().z+2);
		defStat.setPosition(80, -110, this.getPosition().z+2);
		hpStat.setPosition(80, -170, this.getPosition().z+2);
		hungerStat.setPosition(80, -230, this.getPosition().z+2);
		moodStat.setPosition(80, -280, this.getPosition().z+2);
		
		this.addComponent(atkStat);
		this.addComponent(defStat);
		this.addComponent(hpStat);
		this.addComponent(hungerStat);
		this.addComponent(moodStat);
		this.addComponent(nameL);
		
		dragon =d;
		showen = true;
	}
	
	public void hide(){
		this.removeComponent(atkStat);
		this.removeComponent(defStat);
		this.removeComponent(hpStat);
		this.removeComponent(hungerStat);
		this.removeComponent(moodStat);
		this.removeComponent(nameL);
		
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
		backB.setPosition(backB.getPosition().x, backB.getPosition().y,z+2);
		atkStat.setPosition(atkStat.getPosition().x, atkStat.getPosition().y,z+2);
		defStat.setPosition(defStat.getPosition().x, defStat.getPosition().y,z+2);
		hpStat.setPosition(hpStat.getPosition().x, hpStat.getPosition().y,z+2);
		hungerStat.setPosition(hungerStat.getPosition().x, hungerStat.getPosition().y,z+2);
		moodStat.setPosition(moodStat.getPosition().x, moodStat.getPosition().y,z+2);
		nameL.setPosition(nameL.getPosition().x, nameL.getPosition().y,z+2);
	}
}
