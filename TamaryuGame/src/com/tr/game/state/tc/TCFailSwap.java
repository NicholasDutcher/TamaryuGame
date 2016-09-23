package com.tr.game.state.tc;

public class TCFailSwap extends TCSwap {
	private boolean back = false;

	public TCFailSwap(TCFruit from, TCFruit to, TCFruit[][] arr) {
		super(from, to, arr);
	}
	
	public boolean update(){
		boolean ready = true;
		ready &= from.update();
		ready &= to.update();
		
		if(ready){
			if(!back){
				this.from.setTargetPos((int)to.getPosition().x, (int)to.getPosition().y);
				this.to.setTargetPos((int)from.getPosition().x, (int)from.getPosition().y);
				back = true;
				ready = false;
				return back && ready;
			}else{
				return ready;
			}
		}
		
		return ready;
	}

}
