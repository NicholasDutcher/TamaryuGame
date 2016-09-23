package com.tr.game.objects;

import com.tr.engine.gameobject.Actor;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.game.objects.dragons.DragonAnimation;
import com.tr.gl.core.Point3D;

public class Dragon extends Actor {

	public static final boolean MALE = true;
	public static final boolean FEMALE = false;
	public static final long ROUNDTIME = 3000;

	protected String name = "Dragon";
	protected String parent1;
	protected String parent2;

	protected int id;
	protected int hp = 0, mp = 0, stamina = 0;
	protected int hpMax = 0, mpMax = 0, staminaMax = 0;
	protected int baseHp = 0, baseMp = 0, baseStamina = 0;
	protected int hpRank, mpRank, staminaRank, attackRank, defenseRank, speedRank;
	protected int attack = 0, defense = 0, speed = 0;
	protected int baseAttack = 0, baseDefense = 0, baseSpeed = 0;
	protected int mood = 0, hunger = 0, thirst = 0;
	protected int type, stage;
	protected boolean sex;

	protected float fatigue, sickness;
	protected float weight, size;
	protected long birthdate;

	protected long roundStarttime = 0;
	protected boolean newRound = false;
	
	protected boolean canMove = true;
	protected boolean canFly = true;
	protected boolean canSwim = false;
	protected boolean allowIdleMove = true;
	
	protected volatile boolean idle = true;
	protected volatile boolean moving = false;
	protected volatile boolean flying = false;
	protected volatile boolean selected = false;
	protected volatile Point3D targetPos = new Point3D(0,0,0);

	protected Color dragonColor, eyeColor, eyeColor2;

	//egg constructor
	//gets egg, makes dragon
	public Dragon(DragonEgg egg, String n) {
		super(0, 0, null);
		// this.id = from server?

		this.name = n;
		this.id = egg.getId();

		this.parent1 = egg.getParent1();
		this.parent2 = egg.getParent2();
		this.type = egg.getType();
		
		this.hp = egg.getBaseHp();
		this.hpMax = egg.getBaseHp();
		this.baseHp = egg.getBaseHp();
		this.hpRank = egg.getHpRank();
		
		this.mp = egg.getBaseMp();
		this.mpMax = egg.getBaseMp();
		this.baseMp = egg.getBaseMp();
		this.mpRank = egg.getMpRank();

		this.stamina = egg.getBaseStamina();
		this.staminaMax = egg.getBaseStamina();
		this.baseStamina = egg.getBaseStamina();
		this.staminaRank = egg.getStaminaRank();

		this.attack = egg.getBaseAttack();
		this.baseAttack = egg.getBaseAttack();
		this.attackRank = egg.getAttackRank();

		this.defense = egg.getBaseDefense();
		this.baseDefense = egg.getBaseDefense();
		this.defenseRank = egg.getDefenseRank();

		this.speed = egg.getBaseSpeed();
		this.baseSpeed = egg.getBaseSpeed();
		this.speedRank = egg.getSpeedRank();

		this.mood = 100;
		this.hunger = 100;
		this.thirst = 100;
		this.stage = 0;
		this.sex = (Math.random() > 0.5f);

		//this.fatigue = 0;
		//this.sickness = 0;
		// this.weight = (float) Math.random()+1; genau implementierung hängt
		// von default ab...
		// this.size = (float) Math.random()+1; genau implementierung hängt von
		// default ab...

		this.birthdate = System.currentTimeMillis();
	}
	
	//old constructor for testing
	public Dragon() { // full random 1-10, no overall caps
		super(0, 0, null);
		// this.id = from server?
		this.baseHp = (int) (Math.random() * 5f + 5);
		this.hp = baseHp;
		this.hpMax = baseHp;
		this.hpRank = baseHp / 2;
		this.baseMp = (int) (Math.random() * 5f + 5);
		this.mp = baseMp;
		this.mpMax = baseMp;
		this.mpRank = baseMp / 2;
		this.baseStamina = (int) (Math.random() * 5f + 5);
		this.stamina = baseStamina;
		this.staminaMax = baseStamina;
		this.staminaRank = baseStamina / 2;
		this.baseAttack = (int) (Math.random() * 5f + 5);
		this.attack = baseAttack;
		this.attackRank = baseAttack / 2;
		this.baseDefense = (int) (Math.random() * 5f + 5);
		this.defense = baseDefense;
		this.defenseRank = baseDefense / 2;
		this.baseSpeed = (int) (Math.random() * 5f + 5);
		this.speed = baseSpeed;
		this.speedRank = baseSpeed / 2;

		// this.mood = full;
		// this.hunger = full;
		// this.thirst = full;
		// this.type = not planned yet;
		this.stage = 0;
		this.sex = (Math.random() > 0.5f);
		this.fatigue = 0;
		this.sickness = 0;
		// this.weight = (float) Math.random()+1; genau implementierung hängt
		// von default ab...
		// this.size = (float) Math.random()+1; genau implementierung hängt von
		// default ab...

		this.birthdate = System.currentTimeMillis();
		// rndm werte in abhängigekit von min max (noch mal ausmachen)
	}


	public void setName(String s) {
		this.name = s;
	}

	public String getName() {
		return this.name;
	}

	public String getParent1(){
		return this.parent1;
	}

	public String getParent2(){
		return this.parent2;
	}

	public int getId(){
		return this.id;
	}

	public int getHp() {
		return this.hp;
	}

	public int getHpMax() {
		return this.hpMax;
	}

	public int getBaseHp() {
		return this.baseHp;
	}

	public int getHpRank() {
		return this.hpRank;
	}

	public int getMp() {
		return this.mp;
	}

	public int getMpMax() {
		return this.mpMax;
	}

	public int getBaseMp() {
		return this.baseMp;
	}

	public int getMpRank() {
		return this.mpRank;
	}

	public int getStamina() {
		return this.stamina;
	}

	public int getStaminaMax() {
		return this.staminaMax;
	}

	public int getBaseStamina() {
		return this.baseStamina;
	}

	public int getStaminaRank() {
		return this.staminaRank;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getBaseAttack() {
		return this.baseAttack;
	}

	public int getAttackRank() {
		return this.attackRank;
	}

	public int getDefense() {
		return this.defense;
	}

	public int getBaseDefense() {
		return this.baseDefense;
	}

	public int getDefenseRank() {
		return this.defenseRank;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getBaseSpeed() {
		return this.baseSpeed;
	}

	public int getSpeedRank() {
		return this.speedRank;
	}

	public int getMood() {
		return this.mood;
	}

	public int getHunger() {
		return this.hunger;
	}

	public int getThirst() {
		return this.thirst;
	}

	public int getType() {
		return this.type;
	}

	public int getStage() {
		return this.stage;
	}

	public boolean getSex() {
		return this.sex;
	}

	public float getFatigue() {
		return this.fatigue;
	}

	public float getSickness() {
		return this.sickness;
	}

	public float getWeight() {
		return this.weight;
	}

	public float getSizeOfDragon() {
		return this.size;
	}

	public String toString() { // adapt to new values
		StringBuilder sb = new StringBuilder();
		sb.append("_Dragon_State________________________________________" + "\r\n");
		sb.append("Name: " + name + " (" + (sex ? "Male" : "Female") + ")\r\n");
		sb.append("hp: " + hp + " || mp: " + mp + " || stamina: " + stamina + "\r\n");
		sb.append("attack: " + attack + " || defense: " + defense + " || speed: " + speed + "\r\n");
		sb.append("mood: " + mood + " || hunger: " + hunger + " || thirst: " + thirst + "\r\n");
		sb.append("type: " + type + " || weight: " + weight + " || size: " + size + "\r\n");
		sb.append("_____________________________________________________" + "\r\n");
		// age? geht das mit methoden genau wie mit attributen?
		return sb.toString();
	}

	public void feed(Food f) {
		// this.race += f.;
		if(this.hunger + f.hunger <= 100){		
		this.hp += f.hp;
		this.mp += f.mp;
		this.stamina += f.stamina;
		this.attack += f.attack;
		this.defense += f.defense;
		this.speed += f.speed;
		this.mood += f.mood;
		this.hunger += f.hunger;
		this.thirst += f.thirst;
		//this.weight += f.weight;
		//this.size += f.size;
		// if(f.sex != null) this.sex = f.sex; //default von boolean?
		
		//remove food from invetory
		}else{
			//action denied
		}
	}

	public void sleep() { // erhöht stamina regeneration, mood... evtl
							// beeinflussung durch andere stats (voller magen)

	}

	public void wakeUp() {
		// NEW: beenden des schlafzustandes... sleep soll nicht zeitgebunden
		// sein. alternative: methode über zeit?

	}

	public float getAge() { // zu datum machen... datumsstring?
		return System.currentTimeMillis() - birthdate;

	}

	/*
	 * approaches: percent of base overall, dr by hyperbel, statcap
	 * beeinflussung durch base values.
	 */


	public int alteredRandom() {
		double up = Math.random();
		if (up > 0.9) {
			return 5;
		} else if (up > 0.75) {
			return 4;
		} else if (up > 0.55) {
			return 3;
		} else if (up > 0.35) {
			return 2;
		} else if (up > 0.1) {
			return 1;
		} else {
			return 0;
		}

	}

	// makes rank vary when breeding
	public int rankDivergence() {
		double up = Math.random();
		if (up > 0.90) {
			return 2;
		} else if (up > 0.60) {
			return 1;
		} else if (up > 0.40) {
			return 0;
		} else if (up > 0.10) {
			return -1;
		} else {
			return -2;
		}

	}

	// method for trying if the
	// checked integer is within the limits of the ranks
	public int checkRank(int i) {
		int topRank = 5;
		int botRank = 0;

		if (i > topRank) {
			return topRank;
		} else if (i > botRank) {
			return i;
		} else {
			return botRank;
		}
	}

	public DragonEgg breed(Dragon that){
		int bHp = (this.getBaseHp() + that.getBaseHp()) / 2 - 2 + alteredRandom();
		bHp = (bHp > 10) ? 10 : bHp;

		int bMp = (this.getBaseMp() + that.getBaseMp()) / 2 - 2 + alteredRandom();
		bMp = (bMp > 10) ? 10 : bMp;
		int bStam = (this.getBaseStamina() + that.getBaseStamina()) / 2 - 2 + alteredRandom();
		bStam = (bStam > 10) ? 10 : bStam;
		int bAtk = (this.getBaseAttack() + that.getBaseAttack()) / 2 - 2 + alteredRandom();
		bAtk = (bAtk > 10) ? 10 : bAtk;
		int bDef = (this.getBaseDefense() + that.getBaseDefense()) / 2 - 2 + alteredRandom();
		bDef = (bDef > 10) ? 10 : bDef;
		int bSpd = (this.getBaseSpeed() + that.getBaseSpeed()) / 2 - 2 + alteredRandom();
		bSpd = (bSpd > 10) ? 10 : bSpd;		

		int hpR = (this.getHpRank() + that.getHpRank()) / 2 + rankDivergence();
		hpR = checkRank(hpR);

		int mpR = (this.getMpRank() + that.getMpRank()) / 2 + rankDivergence();
		mpR = checkRank(mpR);
		int stamR = (this.getStaminaRank() + that.getStaminaRank()) / 2 + rankDivergence();
		stamR = checkRank(stamR);
		int atkR = (this.getAttackRank() + that.getAttackRank()) / 2 + rankDivergence();
		atkR = checkRank(atkR);
		int defR = (this.getDefenseRank() + that.getDefenseRank()) / 2 + rankDivergence();
		defR = checkRank(defR);
		int spdR = (this.getSpeedRank() + that.getSpeedRank()) / 2 + rankDivergence();
		spdR = checkRank(spdR);
		
		return new DragonEgg(bHp, hpR, bMp, mpR, bStam, stamR, bAtk, atkR, bDef, defR, bSpd, spdR, this.getName(), that.getName());
	}
	
	public void evolve() { // details noch zu klären

	}

	public void die() { // details noch zu klären

	}

	// reduce values based on passed time
	public void statDecay() {
		if (this.hunger > 0 && this.thirst > 0)
			this.hp += 5;
		this.mood += -1;
		this.hunger += -1;
		this.thirst += -1;
	}

	// cut stats (negative to 0, cap at max)
	public void correctStats() {
		if (this.hp < 0) {
			this.hp = 0;
		} else if (this.hp > this.hpMax) {
			this.hp = this.hpMax;
		}

		if (this.mp < 0) {
			this.mp = 0;
		} else if (this.mp > this.mpMax) {
			this.mp = this.mpMax;
		}

		if (this.stamina < 0) {
			this.stamina = 0;
		} else if (this.stamina > this.staminaMax) {
			this.stamina = this.staminaMax;
		}

		if (this.attack < 0)
			this.attack = 0;
		if (this.defense < 0)
			this.defense = 0;
		if (this.speed < 0)
			this.speed = 0;

		if (this.mood < 0) {
			this.mood = 0;
		} else if (this.mood > 100) {
			this.mood = 100;
		}

		if (this.hunger < 0) {
			this.hunger = 0;
		} else if (this.hunger > 100) {
			this.hunger = 100;
		}

		if (this.thirst < 0) {
			this.thirst = 0;
		} else if (this.thirst > 100) {
			this.thirst = 100;
		}

	}

	public void update(long tc) {
		//System.out.println("Update 1");
		long time = tc;

		// idle animations
		updateAction(time);

		if ((time - roundStarttime) > ROUNDTIME) {
			roundStarttime = time;
			newRound = true;
		}
		
		// remaining update logic.
		if (newRound) {
			statDecay();
			correctStats();
			roundAction(time);
		}
		newRound = false;

	}
	
	protected void roundAction(long time){
		// override me
	}
	
	public void updateAction(long time){
		// override me
	}

	// evtl save und load im drachen
	
	
	public boolean onDrop(IRenderable r){
		// TODO
		return false;
	}

	// animation methods
	public Point3D getPosition(){
		if(this.getImage() != null){
			return this.getImage().getPosition();
		}
		return null;
	}
	
	public int[] getFieldSize(){
		return new int[]{((DragonAnimation) getImage()).getFieldWidth(), ((DragonAnimation) getImage()).getFieldHeight()};
	}
	
	public void setColor(Color c) {
		this.dragonColor = c;
		if (dragonColor != null) {
			float r = c.r, g = c.g, b = c.b;
			//System.out.println("Color (p): ["+r+", "+g+", "+b+"]");
			getImage().setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_HSL_FILTER, 1, r, g, b, 1));
			//System.out.println("Color: ["+r+", "+g+", "+b+"]");
		} else {
			getImage().setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_HSL_FILTER, 0, 0, 0, 0, 1));
		}
		setEyeColor(eyeColor);
	}

	public void setEyeColor(Color c) {
		if(this.eyeColor != c){
			calcSecondEyeColor(c, 12f);
		}
		this.eyeColor = c;
		if (eyeColor != null) {
			float r = c.r, g = c.g, b = c.b;
			//System.out.println("Eye Color: ["+r+", "+g+", "+b+"]");
			getImage().getComponentByName("head.eyes.open")
					.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_HSL_FILTER, 1, r, g, b, 1));
		} else {
			getImage().getComponentByName("head.eyes.open")
					.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_HSL_FILTER, 0, 0, 0, 0, 1));
		}
	}
	
	protected void calcSecondEyeColor(Color c, float step){
		float r = c.r, g = c.g, b = c.b;
		if (c.r <= 1 && c.g <= 1 && c.b <= 1 && c.a <= 1) {
			r = r * 255f;
			g = g * 255f;
			b = b * 255f;
		}
		r += step; 
		g += step; 
		b += step;
		r =  Math.max(Math.min(r, 255), 0);
		g =  Math.max(Math.min(g, 255), 0);
		b =  Math.max(Math.min(b, 255), 0);
		
		this.eyeColor2 = new Color(r/255f, g/255f, b/255f, 1);
	}
	
	public void lookRight(){
		((TRGLAnimationView) this.getImage()).loadAnimation("lookRight");
		((TRGLAnimationView) this.getImage()).start();
	}
	
	public void fly(boolean start){
		if(!this.canFly){
			return;
		}
		if(start){
			int speed = (int) Math.round(Math.random()*10+4);
			((TRGLAnimationView) this.getImage().getComponentByName("wings")).loadAnimation("fly", speed);
		}else{
			((TRGLAnimationView) this.getImage().getComponentByName("wings")).loadAnimation("default");
		}
		((TRGLAnimationView) this.getImage().getComponentByName("wings")).start();
	}
	
	public void walk(boolean start){
		if(!this.canMove){
			return;
		}
		if(start){
			int speed = (int) Math.round(Math.random()*10+4);
			((TRGLAnimationView) this.getImage().getComponentByName("legs")).loadAnimation("move", speed);
		}else{
			((TRGLAnimationView) this.getImage().getComponentByName("legs")).loadAnimation("default");
		}
		((TRGLAnimationView) this.getImage().getComponentByName("legs")).start();
	}
	
	public void waveTail(boolean start){
		if(start){
			int speed = (int) Math.round(Math.random()*7+5);
			((TRGLAnimationView) this.getImage().getComponentByName("tail")).loadAnimation("wave", speed);
		}else{
			((TRGLAnimationView) this.getImage().getComponentByName("tail")).loadAnimation("default");
		}
		((TRGLAnimationView) this.getImage().getComponentByName("tail")).start();
	}
	
	public void lookLeft(){
		((TRGLAnimationView) this.getImage()).loadAnimation("lookLeft");
		((TRGLAnimationView) this.getImage()).start();
	}

	public void closeEyes() {
		((TRGLAnimationView) this.getImage().getComponentByName("head.eyes")).loadAnimation("closed");
		((TRGLAnimationView) this.getImage().getComponentByName("head.eyes")).start();
	}

	public void openEyes() {
		((TRGLAnimationView) this.getImage().getComponentByName("head.eyes")).loadAnimation("default");
		((TRGLAnimationView) this.getImage().getComponentByName("head.eyes")).start();
	}

	public void blink() {
		((TRGLAnimationView) this.getImage().getComponentByName("head.eyes")).loadAnimation("blink");
		((TRGLAnimationView) this.getImage().getComponentByName("head.eyes")).start();
	}

}
