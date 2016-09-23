package com.tr.game.state.tc;

import java.util.ArrayList;

import com.tr.engine.gameobject.AbstractGameObject;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.input.ITRGlobalMouseListener;
import com.tr.engine.input.TRGlobalMouseEvent;
import com.tr.gl.core.GLCamera;

public class TamaCombine extends AbstractGameObject implements TCDropListener, ITRGlobalMouseListener {
	public final static int ROW_COUNT = 9;
	public final static int COLUMN_COUNT = 9;

	private TCInterface ui;

	private TCFruit[][] fieldArr = new TCFruit[COLUMN_COUNT][ROW_COUNT];
	private int swipFromCol = -1;
	private int swipFromRow = -1;
	
	private ETCFruits matchType;
	
	private int level = 0;
	private int baseScoreTarget = 2000;
	private float levelScoreFactor = 0.5f;
	private int matchScore = 50;
	private float matchScoreFactor = 1.25f;

	private volatile ArrayList<TCSwap> swaps = new ArrayList<TCSwap>();
	private volatile ArrayList<TCSwap> swapsDelete = new ArrayList<TCSwap>();
	private volatile Object swapLock = new Object();
	private volatile boolean swaping = false;
	private volatile boolean movedown = false;
	private volatile boolean removing = false;
	private volatile boolean fillup = false;
	private volatile boolean ready = false;
	private volatile boolean end = false;
	
	private volatile ArrayList<TCSwap> possibleSwaps = new ArrayList<TCSwap>();

	public TamaCombine(int winHeight) {
		super(0, 0, 0, 0);
		ui = new TCInterface(winHeight, COLUMN_COUNT, ROW_COUNT, this);
		matchType = ETCFruits.random();
		ui.setMaxScore(baseScoreTarget + (baseScoreTarget*level*level*levelScoreFactor));
		ui.setMatchType(new TCFruit(matchType));
		// TODO
		fillRandom();
	}

	private void fillRandom() {
		ETCFruits t = null;
		do{
			for (int i = 0; i < COLUMN_COUNT; i++) {
				for (int j = 0; j < ROW_COUNT; j++) {
					do {
						t = ETCFruits.random();
					} while ((j >= 2 && fieldArr[i][j-2].type == t && fieldArr[i][j-1].type == t)
							|| (i >= 2 && fieldArr[i-1][j].type == t && fieldArr[i-1][j].type == t));
	
					TCFruit f = new TCFruit(t);
					f.column = i;
					f.row = j;
					fieldArr[i][j] = f;
					ui.addFruit(f, i, j);
				}
			}
			detectPossibleMoves();
		}while(possibleSwaps.size() == 0);
		end = false;
	}
	
	private void detectPossibleMoves(){
		possibleSwaps.clear();

		/*//debug
		//System.out.println("Empty Slots in column "+c+": "+emptySlots);
		float[] fa = new float[9*9];
		for(int j = 8; j>=0; j--){
			for(int i = 0; i<9; i++){
				fa[j*9+i] = (fieldArr[i][j] != null)?1:0;
			}
		}
		GLCamera.printFloatMatrix(fa, 9, 9, true);*/
		
		for (int i = 0; i < COLUMN_COUNT; i++) {
			for (int j = 0; j < ROW_COUNT; j++) {
				TCFruit f = getElement(i, j);
				TCFruit t = null;
				
				if(f != null){
					//to the right
					if(i < COLUMN_COUNT - 1){
						t = getElement(i+1, j);
						directSwap(f, t);
						
						if(hasChain(i+1, j) || hasChain(i, j)){
							directSwap(t,f);
							possibleSwaps.add(new TCSwap(f, t, fieldArr));
						}else{
							directSwap(t,f);
						}
						
						
					}
					
					//to the top
					if(j < ROW_COUNT - 1){
						t = getElement(i, j+1);
						directSwap(f, t);
						
						if(hasChain(i, j+1) || hasChain(i, j)){
							//System.out.println("["+i+", "+j+"] Pos swap!");
							directSwap(t, f);
							possibleSwaps.add(new TCSwap(f, t, fieldArr));
						}else{
							directSwap(t,f);
						}
						
						
					}
					
				}
			}
		}
		
		System.out.println("Possible Moves: "+possibleSwaps.size());
		if(possibleSwaps.size() <= 0)
			end = true;
	}
	
	private boolean hasChain(int col, int row){
		int hl = 1, vl = 1;
		
		//hor
		for(int i = col-1; i >= 0 && getElement(i, row).type.equals(getElement(col,row).type); i--){
			hl++;
		}
		for(int i = col+1; i < COLUMN_COUNT  && getElement(i, row).type.equals(getElement(col,row).type); i++){
			hl ++;
		}
		if(hl >= 3)
			return true;
		
		//vert
		for(int i = row-1; i >= 0 && getElement(col, i).type.equals(getElement(col,row).type); i--){
			vl ++;
		}
		for(int i = row+1; i < ROW_COUNT && getElement(col, i).type.equals(getElement(col,row).type); i++){
			vl ++;
		}
		if(vl >= 3)
			return true;
		
		
		return false;
	}
	
	private void directSwap(TCFruit from, TCFruit to){
		/*System.out.println("Before Swap:");
		System.out.println("From: ["+from.row+", "+from.column+"]; "+
				"To: ["+to.row+", "+to.column+"]; ");*/
		int tmpC = from.column;
		int tmpR = from.row;
		from.column = to.column;
		from.row = to.row;
		fieldArr[to.column][to.row] = from;
		
		to.column = tmpC;
		to.row = tmpR;
		fieldArr[tmpC][tmpR] = to;
		/*System.out.println("After Swap:");
		System.out.println("From: ["+from.row+", "+from.column+"]; "+
				"To: ["+to.row+", "+to.column+"]; ");*/
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
			TCSwap sw = new TCSwap(fieldArr[swipFromCol][swipFromRow], fieldArr[targetCol][targetRow], fieldArr);
			if(possibleSwaps.contains(sw)){
				sw.swap();
				swaps.add(sw);
			}else{
				TCFailSwap fsw = new TCFailSwap(fieldArr[swipFromCol][swipFromRow], fieldArr[targetCol][targetRow], fieldArr);
				fsw.swap();
				swaps.add(fsw);
			}
		}
	}

	private TCFruit getElement(int x, int y) {
		return fieldArr[x][y];
	}

	public IRenderable getField() {
		return ui;
	}
	
	private ArrayList<TCFruit> searchMatches(){
		ArrayList<TCFruit> fruits = new ArrayList<TCFruit>();
		ArrayList<TCChain> tmp = new ArrayList<TCChain>();
		
		tmp = searchHChains();
		tmp.addAll(searchVChains());
		for(int i = 0; i< tmp.size(); i++){
			int chainScore = matchScore*3+Math.round((tmp.get(i).getLength()-3)*matchScore*matchScoreFactor);
			if(tmp.get(i).type.equals(matchType) && ui.addScore(chainScore)){
				level++;
				ui.setCount(level);
				ui.setMaxScore(baseScoreTarget + (baseScoreTarget*level*level*levelScoreFactor));
			}
			for(TCFruit f : tmp.get(i).fruits){
				if(!fruits.contains(f)){
					fruits.add(f);
				}
			}
		}
		
		return fruits;
	}
	
	private ArrayList<TCChain> searchHChains(){
		ArrayList<TCChain> chains = new ArrayList<TCChain>();
		
		for(int r = 0; r < ROW_COUNT; r++){
			for(int c = 0; c < COLUMN_COUNT-2; ){
				if(getElement(r,c) != null){
					ETCFruits type = getElement(r,c).type;
					if(getElement(r, c+1) != null && getElement(r, c+2) != null &&
							getElement(r, c+1).type.equals(type) && getElement(r, c+2).type.equals(type)){
						TCChain chain = new TCChain();
						chain.chainType = TCChain.H_CHAIN;
						chain.type = type;
						
						do{
							chain.addFruit(getElement(r,c));
							c++;
						}while(c < COLUMN_COUNT && getElement(r,c) != null && getElement(r, c).type.equals(type));
						
						chains.add(chain);
						continue;
					}
				}
				c++;
			}
		}
		
		return chains;
	}
	
	private ArrayList<TCChain> searchVChains(){
		ArrayList<TCChain> chains = new ArrayList<TCChain>();
		
		for(int c = 0; c < COLUMN_COUNT; c++){
			for(int r = 0; r < ROW_COUNT-2; ){
				if(getElement(r,c) != null){
					ETCFruits type = getElement(r,c).type;
					if(getElement(r+1, c) != null && getElement(r+2, c) != null &&
							getElement(r+1, c).type.equals(type) && getElement(r+2, c).type.equals(type)){
						TCChain chain = new TCChain();
						chain.chainType = TCChain.V_CHAIN;
						chain.type = type;
						
						do{
							chain.addFruit(getElement(r,c));
							r++;
						}while(r < ROW_COUNT && getElement(r,c) != null && getElement(r, c).type.equals(type));
						
						chains.add(chain);
						continue;
					}
				}
				r++;
			}
		}
		
		return chains;
	}

	@Override
	public void update(long ct) {
		if(end){
			System.out.println("No moves left!");
			return;
		}
		synchronized (swapLock) {
			//if swaps to do
			if (swaps.size() > 0) {
				//set swaping to true
				swaping = true;

				//move all swaping tiles;
				for(TCSwap sw : swaps){
					if(sw.update()){
						swapsDelete.add(sw);
					}
				}
				
				//delete all ready tiles
				//System.out.println("Swaps left: "+swaps.size());
				for(TCSwap sw : swapsDelete){
					swaps.remove(sw);
				}
				swapsDelete.clear();
				//System.out.println("Swaps left after remove: "+swaps.size());
			}else{
				if(swaping){
					//swaping is done
					
					//remove matches, if not allready done
					if(!removing && !fillup && !movedown){
						ready = !this.removeMatches();
						removing = true;
						return;
					}
					
					//move down tiles
					if(removing && !fillup && !movedown){
						this.moveDown();
						movedown = true;
						return;
					}
					
					//fillup field, if mathces removed and not allready done
					if(removing && movedown && !fillup){
						this.fillUp();
						fillup = true;
						return;
					}
					
					/*System.out.println("After fillup, full array:");
					StringBuilder sb = new StringBuilder("\r\n\r\n");
					for(int i = 8; i>=0; i--){
						for(int j = 0; j<9; j++){
							TCFruit f = getElement(j,i);
							sb.append((f != null)?("1 ["+f.row+", "+f.column+"]"):0+"\t");
						}
						sb.append("\r\n");
					}
					System.out.println(sb);*/
					
					//detect moves, if field ready
					if(removing && fillup && movedown){
						detectPossibleMoves();
					}
					
					removing = false;
					fillup = false;
					movedown = false;
					
					if(!ready){
						ready = !this.removeMatches();
						removing = true;
						return;
					}
					
					//
					//debug
					/*System.out.println("after filling");
					float[] fa = new float[9*9];
					for(int j = 8; j>=0; j--){
						for(int i = 0; i<9; i++){
							fa[j*9+i] = (fieldArr[i][j] != null)?1:0;
						}
					}
					GLCamera.printFloatMatrix(fa, 9, 9, true);
					GLCamera.printFloatMatrix(fa, 9, 9, true);*/
					swaping = false;
				}
			}
		}
	}
	
	private void fillUp(){
		//System.out.println("Fill up!");
		int emptySlots = 0;
		for(int c = 0; c < COLUMN_COUNT; c++){
			emptySlots = 0;
			for(int r = 0; r < ROW_COUNT; r++){
				TCFruit f = getElement(c, r);
				if(f == null){
					emptySlots++;
				}
			}
			for(int i = 0; i< emptySlots; i++){
				TCFruit f = new TCFruit(ETCFruits.random());
				ui.addFruit(f, c, 0);
				f.setPosition(ui.getTielPos(c, ROW_COUNT+i).x,ui.getTielPos(c, ROW_COUNT+i).y,f.getPosition().z);
				f.column = -1;
				f.row = -1;
				//System.out.println("Add new Item on: ("+c+", "+(ROW_COUNT-emptySlots+i)+") At ["+ui.getTielPos(c, ROW_COUNT-emptySlots+i));
				TCEmptySwap esw = new TCEmptySwap(f, c, (ROW_COUNT-emptySlots+i), 
						ui.getTielPos(c, ROW_COUNT-emptySlots+i), fieldArr);
				swaps.add(esw);
			}
		}
	}
	
	private void moveDown(){
		int emptySlots = 0;
		for(int c = 0; c < COLUMN_COUNT; c++){
			emptySlots = 0;
			for(int r = 0; r < ROW_COUNT; r++){
				TCFruit f = getElement(c, r);
				if(f == null){
					emptySlots++;
				}else{
					//System.out.println("Empty: "+emptySlots);
					if(emptySlots > 0){
						TCEmptySwap esw = new TCEmptySwap(f, c, r-emptySlots, ui.getTielPos(c, r-emptySlots), fieldArr);
						swaps.add(esw);
					}
				}
			}
		}
	}
	
	private boolean removeMatches(){
		ArrayList<TCFruit> matches = searchMatches();
		for(TCFruit m : matches){
			ui.removeFruit(m);
			fieldArr[m.column][m.row] = null;
		}
		
		//
		/*System.out.println("After remove:");
		float[] fa = new float[9*9];
		for(int i = 0; i<9; i++){
			for(int j = 0; j<9; j++){
				fa[i*9+j] = (fieldArr[i][j] != null)?1:0;
			}
		}
		GLCamera.printFloatMatrix(fa, 9, 9, true);*/
		
		return (matches.size() != 0);
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
		if (swipFromCol >= 0 && swipFromRow >= 0) {
			if (swipFromCol < COLUMN_COUNT && swipFromRow < ROW_COUNT) {
				fieldArr[swipFromCol][swipFromRow].setRenderPropertie(
						new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 0, 57 / 255f, 255 / 255f, 20 / 255f, 0));
			}
		}
		swipFromCol = swipFromRow = -1;
	}

	@Override
	public void mouseRelease(TRGlobalMouseEvent e) {
		if (swipFromCol >= 0 && swipFromRow >= 0) {
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
			if(!f.active)
				return;
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
