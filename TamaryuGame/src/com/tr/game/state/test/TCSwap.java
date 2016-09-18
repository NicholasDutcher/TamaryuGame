package com.tr.game.state.test;

public class TCSwap {
	public TCFruit from = null;
	public TCFruit to = null;
	public TCFruit[][] arr = null;
	
	public TCSwap(TCFruit from, TCFruit to, TCFruit[][] arr){
		this.from = from;
		this.to = to;
		this.arr = arr;
	}
	
	public void swap(){
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
	
	public boolean equals(Object e){
		if(e == null)
			return false;
		if(e instanceof TCSwap){
			TCSwap sw = (TCSwap) e;
			boolean fq = false, tq = false;
			if(from == null && sw.from == null){
				fq = true;
			}
			if(to == null && sw.to == null){
				tq = true;
			}
			
			if(fq){
				if(tq || (to != null && to.equals(sw.to)))
					return true;
				return false;
			}else if(tq){
				if(from != null && from.equals(sw.from))
					return true;
				return false;
			}
			
			return ((from.equals(((TCSwap) e).from) && to.equals(((TCSwap) e).to)) ||
					(to.equals(((TCSwap) e).from) && from.equals(((TCSwap) e).to)));
		}
		return false;
	}

}
