package com.tr.game.state.test;

public class TCSwap {
	public TCFruit from = null;
	public TCFruit to = null;
	public TCFruit[][] arr = null;
	
	public TCSwap(TCFruit from, TCFruit to, TCFruit[][] arr){
		this.from = from;
		this.to = to;
		this.arr = arr;
		
		this.from.setTargetPos((int)to.getPosition().x, (int)to.getPosition().y);
		this.to.setTargetPos((int)from.getPosition().x, (int)from.getPosition().y);
	}
	
	public boolean update(){
		boolean ready = true;
		ready &= from.update();
		ready &= to.update();
		
		if(ready){
			int tmpC = from.column;
			int tmpR = from.row;
			from.column = to.column;
			from.row = to.row;
			arr[to.column][to.row] = from;
			
			to.column = tmpC;
			to.row = tmpR;
			arr[tmpC][tmpR] = to;
		}
		
		return ready;
	}

}
