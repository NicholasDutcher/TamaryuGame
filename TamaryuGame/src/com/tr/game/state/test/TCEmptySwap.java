package com.tr.game.state.test;

import com.tr.gl.core.Point3D;

public class TCEmptySwap extends TCSwap {
	private Point3D toP = null;
	private int c, r;

	public TCEmptySwap(TCFruit from, int c, int r, Point3D to, TCFruit[][] arr) {
		super(from, null, arr);
		this.c = c;
		this.r = r;
		this.toP = to;
		
		from.setTargetPos((int)toP.x, (int)toP.y);
	}
	
	public boolean update(){
		boolean ready = true;
		ready &= from.update();
		
		if(ready){
			if(from.column >= 0 && from.row >= 0)
				arr[from.column][from.row] = null;
			from.column = c;
			from.row = r;
			arr[c][r] = from;
			System.out.println("Swaped to: "+c+", "+r);
		}
		return ready;
	}

}
