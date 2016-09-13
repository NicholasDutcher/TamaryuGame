package com.tr.game.objects;

import com.tr.engine.gameobject.Actor;

public class Dragon extends Actor {

	public static final boolean MALE = true;
	public static final boolean FEMALE = false;
	public static final long ROUNDTIME = 3000;

	private String name = "Dragon";
	private String parent1;
	private String parent2;
	
	private int id;
	private int hp = 0, mp = 0, stamina = 0;
	private int hpMax = 0, mpMax = 0, staminaMax = 0;
	private int baseHp = 0, baseMp = 0, baseStamina = 0;
	private int hpRank, mpRank, staminaRank, attackRank, defenseRank, speedRank;
	private int attack = 0, defense = 0, speed = 0;
	private int baseAttack = 0, baseDefense = 0, baseSpeed = 0;
	private int mood = 0, hunger = 0, thirst = 0;
	private int type, stage;
	private boolean sex;

	private float fatigue, sickness;
	private float weight, size;
	private long birthdate;

	private long roundStarttime = 0;
	private boolean newRound = false;

	public Dragon() { // full random 1-10, no overall caps
		// this.id = from server?
		this.baseHp = (int) (Math.random() * 10f);
		this.hp = baseHp;
		this.hpRank = baseHp / 2;
		this.baseMp = (int) (Math.random() * 10f);
		this.mp = baseMp;
		this.mpRank = baseMp / 2;
		this.baseStamina = (int) (Math.random() * 10f);
		this.stamina = baseStamina;
		this.staminaRank = baseStamina / 2;
		this.baseAttack = (int) (Math.random() * 10f);
		this.attack = baseAttack;
		this.attackRank = baseAttack / 2;
		this.baseDefense = (int) (Math.random() * 10f);
		this.defense = baseDefense;
		this.defenseRank = baseDefense / 2;
		this.baseSpeed = (int) (Math.random() * 10f);
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

	public Dragon(int hpR, int mpR, int staminaR, int attackR, int defenseR, int speedR, String p1, String p2) {
		// this.id = from server?
		this.parent1 = p1;
		this.parent2 = p2;
		this.baseHp = (int) (Math.random() * 10f);
		this.hp = baseHp;
		this.hpRank = hpR;
		this.baseMp = (int) (Math.random() * 10f);
		this.mp = baseMp;
		this.mpRank = mpR;
		this.baseStamina = (int) (Math.random() * 10f);
		this.stamina = baseStamina;
		this.staminaRank = staminaR;
		this.baseAttack = (int) (Math.random() * 10f);
		this.attack = baseAttack;
		this.attackRank = attackR;
		this.baseDefense = (int) (Math.random() * 10f);
		this.defense = baseDefense;
		this.defenseRank = defenseR;
		this.baseSpeed = (int) (Math.random() * 10f);
		this.speed = baseSpeed;
		// this.speedRank = speedR;

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

	// Dragon with values
	public Dragon(int hp, int mp, int stamina, int attack, int defense, int speed) {
		// this.id = from server?
		this.hp = hp;
		this.mp = mp;
		this.stamina = stamina;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.baseHp = hp;
		this.baseMp = mp;
		this.baseStamina = stamina;
		this.baseAttack = attack;
		this.baseDefense = defense;
		this.baseSpeed = speed;
		this.hpRank = hp / 2;
		this.mpRank = mp / 2;
		this.staminaRank = stamina / 2;
		this.attackRank = attack / 2;
		this.defenseRank = defense / 2;
		this.speedRank = speed / 2;
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
	}

	public void setName(String s) {
		this.name = s;
	}

	public String getName() {
		return this.name;
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
		this.hp += f.hp;
		this.mp += f.mp;
		this.stamina += f.stamina;
		this.attack += f.attack;
		this.defense += f.defense;
		this.speed += f.speed;
		this.mood += f.mood;
		this.hunger += f.hunger;
		this.thirst += f.thirst;
		this.weight += f.weight;
		this.size += f.size;

		// if(f.sex != null) this.sex = f.sex; //default von boolean?

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

	/*
	 * simple 50/50 combination. both parents base values /2 and added up
	 */
	public Dragon breed1(Dragon that) {
		int hp = (this.getBaseHp() + that.getBaseHp()) / 2;

		int mp = (this.getBaseMp() + that.getBaseMp()) / 2;
		int stamina = (this.getBaseStamina() + that.getBaseStamina()) / 2;
		int attack = (this.getBaseAttack() + that.getBaseAttack()) / 2;
		int defense = (this.getBaseDefense() + that.getBaseDefense()) / 2;
		int speed = (this.getBaseSpeed() + that.getBaseSpeed()) / 2;

		return new Dragon(hp, mp, stamina, attack, defense, speed);

	}

	/*
	 * from-to random. randomized value between the parents. not that good IMO,
	 * but maybe of some use in another case
	 */
	public Dragon breed2(Dragon that) {
		int maxHp = Math.max(this.getBaseHp(), that.getBaseHp());
		int minHp = Math.min(this.getBaseHp(), that.getBaseHp());
		int hp = minHp + (int) (Math.random() * (float) (maxHp - minHp));

		// rest not implemented yet
		int mp = (this.getBaseMp() + that.getBaseMp()) / 2;
		int stamina = (this.getBaseStamina() + that.getBaseStamina()) / 2;
		int attack = (this.getBaseAttack() + that.getBaseAttack()) / 2;
		int defense = (this.getBaseDefense() + that.getBaseDefense()) / 2;
		int speed = (this.getBaseSpeed() + that.getBaseSpeed()) / 2;

		return new Dragon(hp, mp, stamina, attack, defense, speed);

	}

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

	/*
	 * random +- from parent average. takes the center of the parent's values.
	 * adds value from -3 to 3 (in this case). my personal favorite. no
	 * over-breeding due to cap at 10.
	 * 
	 */
	public Dragon breed3(Dragon that) {
		int hp = (this.getBaseHp() + that.getBaseHp()) / 2 - 2 + alteredRandom();
		hp = (hp > 10) ? 10 : hp;

		int mp = (this.getBaseMp() + that.getBaseMp()) / 2 - 2 + alteredRandom();
		mp = (mp > 10) ? 10 : mp;
		int stamina = (this.getBaseStamina() + that.getBaseStamina()) / 2 - 2 + alteredRandom();
		stamina = (stamina > 10) ? 10 : stamina;
		int attack = (this.getBaseAttack() + that.getBaseAttack()) / 2 - 2 + alteredRandom();
		attack = (attack > 10) ? 10 : attack;
		int defense = (this.getBaseDefense() + that.getBaseDefense()) / 2 - 2 + alteredRandom();
		defense = (defense > 10) ? 10 : defense;
		int speed = (this.getBaseSpeed() + that.getBaseSpeed()) / 2 - 2 + alteredRandom();
		speed = (speed > 10) ? 10 : speed;

		return new Dragon(hp, mp, stamina, attack, defense, speed);

	}
	
	//makes rank vary when breeding
	public int rankDivergence() {
		double up = Math.random();
		if (up > 0.90) {
			return 2;
		} else if (up > 0.60) {
			return 1;
		} else if (up > 0.40) {
			return 0;
		} else if (up > 0.10){
			return -1;
		} else {
			return -2;
		}

	}
	//method for trying if the
	//checked integer is within the limits of the ranks
	public int checkRank(int i){
		int topRank = 5;
		int botRank = 0;

		if (i > topRank){
			return topRank;
		} else if (i > botRank){
			return i;
		} else {
			return botRank;
		}
	}
	
	/*
	 * approach of Breed3
	 * but this only affects the rank.
	 */
	public Dragon breed3b(Dragon that) {
		
		int hp = (this.getHpRank() + that.getHpRank()) / 2 + rankDivergence();
		hp = checkRank(hp);

		int mp = (this.getMpRank() + that.getMpRank()) / 2 + rankDivergence();
		mp = checkRank(mp);
		int stamina = (this.getStaminaRank() + that.getStaminaRank()) / 2 + rankDivergence();
		stamina = checkRank(stamina);
		int attack = (this.getAttackRank() + that.getAttackRank()) / 2 + rankDivergence();
		attack = checkRank(attack);
		int defense = (this.getDefenseRank() + that.getDefenseRank()) / 2 + rankDivergence();
		defense = checkRank(defense);
		int speed = (this.getSpeedRank() + that.getSpeedRank()) / 2 + rankDivergence();
		speed = checkRank(speed);

		//return won't work properly
		//since the constructor is inappropriate
		return new Dragon(hp, mp, stamina, attack, defense, speed, this.getName(), that.getName());
	}

	/*
	 * inheritance by development. not realistic but player oriented. pros:
	 * sticks to the original status overalls. cons: base values go extremely
	 * low when parent is extremely unbalanced
	 */
	public Dragon breed4(Dragon that) {
		float overallBaseThis = this.getBaseHp() + this.getBaseMp() + this.getBaseStamina() + this.getBaseAttack()
				+ this.getBaseDefense() + this.getBaseSpeed();
		float overallThis = this.getHp() + this.getMp() + this.getStamina() + this.getAttack() + this.getDefense()
				+ this.getSpeed();

		float overallBaseThat = that.getBaseHp() + that.getBaseMp() + that.getBaseStamina() + that.getBaseAttack()
				+ that.getBaseDefense() + that.getBaseSpeed();
		float overallThat = that.getHp() + that.getMp() + that.getStamina() + that.getAttack() + that.getDefense()
				+ that.getSpeed();

		int hp = (int) (((float) this.getHp() / overallThis) * overallBaseThis
				+ ((float) that.getHp() / overallThat) * overallBaseThat) / 2;

		// rest not implemented yet
		int mp = (this.getBaseMp() + that.getBaseMp()) / 2;
		int stamina = (this.getBaseStamina() + that.getBaseStamina()) / 2;
		int attack = (this.getBaseAttack() + that.getBaseAttack()) / 2;
		int defense = (this.getBaseDefense() + that.getBaseDefense()) / 2;
		int speed = (this.getBaseSpeed() + that.getBaseSpeed()) / 2;

		return new Dragon(hp, mp, stamina, attack, defense, speed);

	}

	public void evolve() { // details noch zu klären

	}

	public void die() { // details noch zu klären

	}
	
	//reduce values based on passed time
	public void statDecay(){
		if(this.hunger > 0 && this.thirst > 0) this.hp += 5;
		this.mood += -1;
		this.hunger += -1;
		this.thirst += -1;
	}
	
	//cut stats (negative to 0, cap at max)
	public void correctStats(){
		if(this.hp < 0){
			this.hp = 0;
		}else if(this.hp > this.hpMax){
			this.hp = this.hpMax;
		}
		
		if(this.mp < 0){
			this.mp = 0;
		}else if(this.mp > this.mpMax){
			this.mp = this.mpMax;
		}
		
		if(this.stamina < 0){
			this.stamina = 0;
		}else if(this.stamina > this.staminaMax){
			this.stamina = this.staminaMax;
		}
		
		if(this.attack < 0) this.attack = 0;
		if(this.defense < 0) this.defense = 0;
		if(this.speed < 0) this.speed = 0;
		
		if(this.mood < 0){
			this.mood = 0;
		}else if(this.mood > 100){
			this.mood = 100;
		}

		if(this.hunger < 0){
			this.hunger = 0;
		}else if(this.hunger > 100){
			this.hunger = 100;
		}

		if(this.thirst < 0){
			this.thirst = 0;
		}else if(this.thirst > 100){
			this.thirst = 100;
		}

	}

	public void update() {
		long time = System.currentTimeMillis();
		
		

		//idle animations
		
		if ((time - roundStarttime) > ROUNDTIME) {
			roundStarttime = time;
			newRound = true;
		}
		// remaining update logic.
		if(newRound) {
			statDecay();
			correctStats();
		}
		newRound = false;
		

	}

	// evtl save und load im drachen

}
