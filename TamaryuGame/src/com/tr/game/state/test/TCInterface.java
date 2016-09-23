package com.tr.game.state.test;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.gameobject.AbstractGameObject;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.game.objects.TamaProgressBar;
import com.tr.gl.core.GLCamera;
import com.tr.gl.core.Point3D;
import com.tr.util.LanguageTranslator;

public class TCInterface extends TRGLImageView {
	private int w = 400;
	private int h = 600;
	private float s = 0f;
	
	private int fieldWidth = 400;
	private int fieldHeight = 400;
	private int elementW = 0;
	private int elementH = 0;
	
	private int fc = 0, fr = 0;
	private TRGLImageView field = new TRGLImageView();
	private TRLabel titel = TRComponentManager.getLabel(LanguageTranslator.getString("gametitel"));
	private TamaProgressBar progress = new TamaProgressBar(0, 2000);
	private TCFruit matchType = null;
	private TRLabel timesLabel = TRComponentManager.getLabel(" x ");
	private TRLabel countLabel = TRComponentManager.getLabel("0");
	
	private TCDropListener dl = null;
	
	public TCInterface(int height, int column, int row, TCDropListener dl){
		this.dl = dl;
		this.fc = column;
		this.fr = row;
		elementW = fieldWidth / fc;
		elementH = fieldHeight / fr;
		s = (float)height/(float)h;
		this.setPosition(0, 0, 30);
		this.setSize(w, h);
		this.setFixedPosition(FIXED_POS_CENTER);
		//this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_TEXTURE, 0,0,0,1,1));
		//field.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_TEXTURE, 0,0,1,0,1));
		
		titel.setPosition((w-titel.getWidth())/2, h-(30+38), 5);
		titel.setColor(Color.ORANGE);
		this.addComponent(titel);
		
		progress.setSize((int) Math.round(fieldWidth * 0.6), 20);
		progress.setPosition(20+42+5+timesLabel.getWidth()+5+40,(h-fieldHeight)/3*2-50+Math.round((42-38)/2)+10, 32);
		progress.setValue(0);
		progress.setStepSize(0.04f);
		this.addComponent(progress.getRenderable());
		
		timesLabel.setPosition(20+42+5, (h-fieldHeight)/3*2-50+Math.round((42-38)/2)+2, 32);
		this.addComponent(timesLabel);
		
		countLabel.setPosition(20+42+5+timesLabel.getWidth()+5, (h-fieldHeight)/3*2-50+Math.round((42-38)/2), 32);
		this.addComponent(countLabel);
		
		initField();
	}
	
	public void setCount(int i){
		countLabel.setText(""+i);
	}
	
	public void setMatchType(TCFruit f){
		if(matchType != null)
			this.removeComponent(matchType);
		matchType = f;
		
		matchType.setRenderPropertie(
				new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 3, 57 / 255f, 255 / 255f, 20 / 255f, 0));
		matchType.active = false;
		matchType.setSize(42, 42);
		matchType.setPosition(20, (h-fieldHeight)/3*2-50, 32);
		
		this.addComponent(matchType);
	}
	
	public void setMaxScore(float v){
		progress.setValue(0);
		progress.setMaxValue(v);
	}
	
	public boolean addScore(float v){
		return progress.increaseValue(v);
	}
	
	public AbstractGameObject[] getUpdateable(){
		return new AbstractGameObject[]{};
	}
	
	public Point3D getTielPos(int c, int r){
		return new Point3D(c*elementW, r*elementH, 0);
	}
	
	private void initField(){
		field.setSize(fieldWidth, fieldHeight);
		field.setPosition((w-fieldWidth)/2, (h-fieldHeight)/3*2, 31);
		field.setClipping(true);
		this.addComponent(field);
		
		GameDropArea da = null;
		for(int r = 0; r < fr; r++){
			for(int c = 0; c < fc; c++){
				da = new GameDropArea(elementW);
				da.setDropListener(dl);
				da.col = r;
				da.row = c;
				da.setPosition(r*elementW+1, c*elementH+1, 32);
				field.addComponent(da);
			}
		}
		
	}
	
	public void addFruit(TCFruit f, int c, int r){
		f.setPosition(c*elementW+1, r*elementH+1, 32);
		f.setSize(elementW, elementH);
		field.addComponent(f);
	}
	
	public void removeFruit(TCFruit f){
		field.removeComponent(f);
	}
	
	public void resize(TRRenderContext rc, int w, int h){
		GLCamera cam = (GLCamera) rc.getScene().getCamera();
		s = (cam.getWinHeigth()*cam.getScale())/this.h;
		this.setScale(s);
		//System.out.println("Div Scale: "+this.getAbsolutScale());
		super.resize(rc, w, h);
	}

}
