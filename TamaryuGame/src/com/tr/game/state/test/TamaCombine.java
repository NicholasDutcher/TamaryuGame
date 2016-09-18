package com.tr.game.state.test;

import java.util.ArrayList;

import com.tr.engine.gameobject.AbstractGameObject;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.input.ITRGlobalMouseListener;
import com.tr.engine.input.TRGlobalMouseEvent;

public class TamaCombine extends AbstractGameObject implements TCDropListener, ITRGlobalMouseListener {
	public final static int ROW_COUNT = 9;
	public final static int COLUMN_COUNT = 9;

	private TCInterface ui;

	private TCFruit[][] fieldArr = new TCFruit[COLUMN_COUNT][ROW_COUNT];
	private int swipFromCol = -1;
	private int swipFromRow = -1;

	private volatile ArrayList<TCSwap> swaps = new ArrayList<TCSwap>();
	private volatile Object swapLock = new Object();
	private volatile boolean swaping = false;
	
	//private volatile ArrayList<TCSwap> possibleSwap

	public TamaCombine(int winHeight) {
		super(0, 0, 0, 0);
		ui = new TCInterface(winHeight, ROW_COUNT, COLUMN_COUNT, this);
		// TODO
		fillRandom();
	}

	private void fillRandom() {
		ETCFruits t = null;
		for (int i = 0; i < ROW_COUNT; i++) {
			for (int j = 0; j < COLUMN_COUNT; j++) {
				do {
					t = ETCFruits.random();
				} while ((j >= 2 && fieldArr[j - 2][i].type == t && fieldArr[j - 1][i].type == t)
						|| (i >= 2 && fieldArr[j][i - 2].type == t && fieldArr[j][i - 1].type == t));

				TCFruit f = new TCFruit(t);
				f.column = j;
				f.row = i;
				fieldArr[j][i] = f;
				ui.addFruit(f, i, j);
			}
		}
	}

	private void trySwap(int hd, int vd) {
		int targetCol = swipFromCol + hd;
		int targetRow = swipFromRow + vd;

		if (targetCol < 0 || targetCol >= COLUMN_COUNT) {
			return;
		}
		if (targetRow < 0 || targetRow >= ROW_COUNT) {
			return;
		}

		fieldArr[swipFromCol][swipFromRow].setRenderPropertie(
				new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 0, 57 / 255f, 255 / 255f, 20 / 255f, 0));

		synchronized (swapLock) {
			swaps.add(new TCSwap(fieldArr[swipFromCol][swipFromRow], fieldArr[targetCol][targetRow], fieldArr));
		}
	}

	private TCFruit getElement(int x, int y) {
		return fieldArr[y][x];
	}

	public IRenderable getField() {
		return ui;
	}

	@Override
	public void update(long ct) {
		synchronized (swapLock) {
			if (swaps.size() > 0) {
				swaping = true;

				if (swaps.get(0).update()) {
					swaps.remove(0);
				}

				if (swaps.size() == 0)
					swaping = false;
			}
		}
	}

	@Override
	public boolean drop(int row, int col, TCFruit fruit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mouseEnter(TRGlobalMouseEvent e) {
	}

	@Override
	public void mouseLeave(TRGlobalMouseEvent e) {
		if (swipFromCol > 0 && swipFromRow > 0) {
			if (swipFromCol < COLUMN_COUNT && swipFromRow < ROW_COUNT) {
				fieldArr[swipFromCol][swipFromRow].setRenderPropertie(
						new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 0, 57 / 255f, 255 / 255f, 20 / 255f, 0));
			}
		}
		swipFromCol = swipFromRow = -1;
	}

	@Override
	public void mouseRelease(TRGlobalMouseEvent e) {
		if (swipFromCol > 0 && swipFromRow > 0) {
			if (swipFromCol < COLUMN_COUNT && swipFromRow < ROW_COUNT) {
				fieldArr[swipFromCol][swipFromRow].setRenderPropertie(
						new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 0, 57 / 255f, 255 / 255f, 20 / 255f, 0));
			}
		}

		swipFromCol = swipFromRow = -1;
	}

	@Override
	public void mousePress(TRGlobalMouseEvent e) {
		if (this.swaping)
			return;
		if (e.src instanceof TCFruit) {
			TCFruit f = (TCFruit) e.src;
			this.swipFromCol = f.column;
			this.swipFromRow = f.row;
			f.setRenderPropertie(
					new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 3, 57 / 255f, 255 / 255f, 20 / 255f, 0));
		}
	}

	@Override
	public void mouseDragged(TRGlobalMouseEvent tre) {
		if (this.swaping)
			return;

		if (this.swipFromCol < 0 || this.swipFromRow < 0) {
			return;
		}

		if (tre.dra != null && tre.dra instanceof GameDropArea) {
			GameDropArea da = (GameDropArea) tre.dra;
			if (da.col != swipFromCol || da.row != swipFromRow) {
				int hd = 0, vd = 0;
				if (da.col < swipFromCol) {
					hd = -1;
				} else if (da.col > swipFromCol) {
					hd = 1;
				} else if (da.row < swipFromRow) {
					vd = -1;
				} else if (da.row > swipFromRow) {
					vd = 1;
				}
				if (hd != 0 || vd != 0) {
					trySwap(hd, vd);
					swipFromCol = swipFromRow = -1;
				}
			}
		}
	}

	@Override
	public void mouseMoved(TRGlobalMouseEvent tre) {
	}

	@Override
	public void mouseClicked(TRGlobalMouseEvent e) {
	}

}
