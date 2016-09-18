package com.tr.game.state.test;

import com.tr.gl.core.Point3D;

public class TCFruit extends GameFood {
	public volatile int column = 0;
	public volatile int row = 0;
	public ETCFruits type = null;

	public TCFruit(ETCFruits fruit) {
		super(fruit.getImg());
		this.type = fruit;
	}
	
	public ETCFruits getType(){
		return this.type;
	}
	
	public Point3D getPos(){
		return new Point3D(row, column, 0);
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}

}
