package com.tr.game.objects;

import com.tr.engine.gameobject.Actor;

public class DragonEgg extends Actor {

	public static final boolean MALE = true;
	public static final boolean FEMALE = false;
	public static final long ROUNDTIME = 3000;

	protected String parent1 = null;
	protected String parent2 = null;

	protected int id;
	protected int baseHp = 0, baseMp = 0, baseStamina = 0, baseAttack = 0, baseDefense = 0, baseSpeed = 0;
	protected int hpRank, mpRank, staminaRank, attackRank, defenseRank, speedRank;
	protected int type, stage;

	protected long roundStarttime = 0;
	protected boolean newRound = false;


	//random Egg
	// full random 1-10
	//no overall caps
	public DragonEgg() {
		super(0, 0, null);
		// this.id = from server?
		this.baseHp = (int) (Math.random() * 5f + 5);
		this.hpRank = baseHp / 2;
		this.baseMp = (int) (Math.random() * 5f + 5);
		this.mpRank = baseMp / 2;
		this.baseStamina = (int) (Math.random() * 5f + 5);
		this.staminaRank = baseStamina / 2;
		this.baseAttack = (int) (Math.random() * 5f + 5);
		this.attackRank = baseAttack / 2;
		this.baseDefense = (int) (Math.random() * 5f + 5);
		this.defenseRank = baseDefense / 2;
		this.baseSpeed = (int) (Math.random() * 5f + 5);
		this.speedRank = baseSpeed / 2;
		// this.type = not implemented yet;
	}

	//breeded Egg
	//ranks inherited, stats random
	//no overall caps
	public DragonEgg(int bhp, int hpr, int bmp, int mpr, int bstam, int stamr, int batk, int atkr, int bdef, int defr, int bspd, int spdr, String p1, String p2) {
		super(0, 0, null);
		// this.id = from server?
		this.parent1 = p1;
		this.parent2 = p2;
		this.baseHp = bhp;
		this.hpRank = hpr;
		this.baseMp = bmp;
		this.mpRank = mpr;
		this.baseStamina = bstam;
		this.staminaRank = stamr;
		this.baseAttack = batk;
		this.attackRank = atkr;
		this.baseDefense = bdef;
		this.defenseRank = defr;
		this.baseSpeed = bspd;
		this.speedRank = spdr;
		//this.type = not implemented yet;
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
	
	public int getBaseHp() {
		return this.baseHp;
	}

	public int getHpRank() {
		return this.hpRank;
	}

	public int getBaseMp() {
		return this.baseMp;
	}

	public int getMpRank() {
		return this.mpRank;
	}

	public int getBaseStamina() {
		return this.baseStamina;
	}

	public int getStaminaRank() {
		return this.staminaRank;
	}

	public int getBaseAttack() {
		return this.baseAttack;
	}

	public int getAttackRank() {
		return this.attackRank;
	}

	public int getBaseDefense() {
		return this.baseDefense;
	}

	public int getDefenseRank() {
		return this.defenseRank;
	}

	public int getBaseSpeed() {
		return this.baseSpeed;
	}

	public int getSpeedRank() {
		return this.speedRank;
	}

	public int getType() {
		return this.type;
	}

	public String toString() { // adapt to new values
		StringBuilder sb = new StringBuilder();
		sb.append("_DragonEgg_State________________________________________" + "\r\n");
		sb.append("Type: " + type + ")\r\n");
		sb.append("hp: " + baseHp + " || mp: " + baseMp + " || stamina: " + baseStamina + "\r\n");
		sb.append("hpRank: " + hpRank + " || mpRank: " + mpRank + " || staminaRank: " + staminaRank + "\r\n");
		sb.append("attack: " + baseAttack + " || defense: " + baseDefense + " || speed: " + baseSpeed + "\r\n");
		sb.append("attack: " + attackRank + " || defense: " + defenseRank + " || speed: " + speedRank + "\r\n");
		
		sb.append("_____________________________________________________" + "\r\n");
		return sb.toString();
	}

	public void hatch(String n) {
		//drache aus ei erzeugen???
		Dragon n = new Dragon(this);
		
	}
	
	protected void roundAction(long time){
		// override me
	}
	
	public void updateAction(long time){
		// override me
	}

}
