package com.tr.game.objects.dragons;

import com.tr.engine.grf.Color;
import com.tr.game.objects.Dragon;
import com.tr.gl.core.Point3D;

import demo.tama.BabyWyvern;

public class Wyvern extends Dragon {
	protected Color curEyeColor = null;
	protected float colorStep = 0.5f;
	
	protected boolean blink = true;
	protected int minBlinkInterval = 1000;
	protected int maxBlinkInterval = 5000;
	protected long nextBlink = 0;
	
	protected boolean tailWaving = false;
	protected int minTailDuration = 1000;
	protected int maxTailDuration = 6000;
	protected int tailRepeatInterval = 10000;
	protected long nextTailChange = 0;
	
	protected boolean idleMoveReady = false;
	protected int minIdleMovePause = 2000;
	protected int maxIdleMovePause = 10000;
	protected long nextIdleMove = 0;
	
	protected boolean firstCall = true;
	
	public Wyvern(){
		super();
		this.setImage(new BabyWyvern(1, this));
		this.idle = true;
	}
	
	public void setIdleMove(boolean b){
		this.allowIdleMove = b;
		if(!b){
			this.targetPos.x = this.getImage().getPosition().x;
			this.targetPos.y = this.getImage().getPosition().y;
		}
	}
	
	protected void roundAction(long time){
		// override me
	}
	
	public void updateAction(long time){
		if(firstCall){
			if(getMaxX() < 0 || getMaxY() < 0)
				return;
			float x = Math.round(Math.random()*getMaxX());
			float y = Math.round(Math.random()*getMaxY());
			float z = -10*(y/100f);
			this.getImage().setPosition(x, y, this.getImage().getPosition().z);
			this.targetPos = new Point3D(x,y,0);
			this.getImage().setZ(z);
			firstCall = false;
		}
		
		if(this.blink){
			if((nextBlink - time) < 0){
				this.blink();
				nextBlink = time + Math.round(Math.random() * (maxBlinkInterval - minBlinkInterval) + minBlinkInterval);
			}
		}
		
		//if idle
		if(this.idle){
			//tail animation
			if((nextTailChange - time) < 0){
				//System.out.println("Tail! ("+tailWaving+")");
				if(tailWaving){
					//stop
					this.waveTail(false);
					tailWaving = false;
					nextTailChange = time + (Math.round(Math.random() * (tailRepeatInterval + maxTailDuration) + minTailDuration));
				}else{
					//start
					this.tailWaving = true;
					this.waveTail(true);
					nextTailChange = time + (Math.round(Math.random() * (maxTailDuration - minTailDuration) + minTailDuration));
				}
			} 
			
			//idle move
			if(this.allowIdleMove && !this.moving && !this.flying){
				if(this.idleMoveReady){
					if((nextIdleMove - time) < 0){
						int maxX = getMaxX();
						int minX = 0, minY = 0;
						int maxY = getMaxY();
						this.targetPos = new Point3D(Math.round(Math.random()*(maxX - minX)+minX), 
								Math.round(Math.random()*(maxY - minY)*0.75f+minY), 0f);
						this.idleMoveReady = false;
						//System.out.println("Image Scale: "+getImage().getScale()+"; Image Size: "+getImage().getWidth()+" x "+getImage().getHeight()+"; FieldWidth: "+this.getFieldSize()[0]);
						//System.out.println("Max Pos: ["+maxX+", "+maxY+"];  Target: "+targetPos);
						if(Math.random() > 0.3){
							this.walk(true);
							this.moving = true;
						}else{
							this.fly(true);
							this.flying = true;
						}
					}
				}else{
					this.idleMoveReady = true;
					nextIdleMove = time + (Math.round(Math.random() * (maxIdleMovePause - minIdleMovePause) + minIdleMovePause));
				}
			}
			
		}

		move();
	}
	
	private int getMaxX(){
		float s = getImage().getScale();
		return (int) ((this.getFieldSize()[0] - this.getImage().getWidth()*s)/s);
	}
	
	private int getMaxY(){
		float s = getImage().getScale();
		return (int) ((this.getFieldSize()[1] - this.getImage().getHeight()*s)/s);
	}
	
	public void move(){
		boolean xr = false, yr = false;
		if(this.getPosition().x == this.targetPos.x){
			xr = true;
		}
		if(this.getPosition().y == this.targetPos.y){
			yr = true;
		}
		
		if(!xr){
			if(this.getPosition().x > this.targetPos.x){
				this.lookLeft();
				this.getImage().setX(Math.max(targetPos.x, getPosition().x-5));
			}else{
				this.lookRight();
				//System.out.println("Set x: "+Math.min(targetPos.x, getPosition().x+5));
				this.getImage().setX(Math.min(targetPos.x, getPosition().x+5));
			}
		}
		
		if(!yr){
			if(this.getPosition().y > this.targetPos.y){
				this.getImage().setY(Math.max(targetPos.y, getPosition().y-5));
			}else{
				this.getImage().setY(Math.min(targetPos.y, getPosition().y+5));
			}
			
			this.getImage().setZ(-10*this.getImage().getPosition().y/100);
		}
		
		if(xr && yr){
			if(this.flying){
				this.fly(false);
				this.flying = false;
			}else if(this.moving){
				this.walk(false);
				this.moving = false;
			}
		}
	}
	


}
