package com.tr.game.state.test;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.gl.TRGLImageView;
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
		
		initField();
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
