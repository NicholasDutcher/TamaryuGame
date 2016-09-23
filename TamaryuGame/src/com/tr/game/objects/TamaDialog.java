package com.tr.game.objects;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRLabel;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.grf.Color;
import com.tr.engine.grf.TRRenderContext;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.gl.core.GLCamera;

public class TamaDialog extends TRGLImageView {
	private int fullW = 800;
	private int fullH = 1000;
	//private int chainH = 215;
	
	private int yUp = fullH;
	private int yDown = 0;
	private boolean up = false;;
	//private boolean ready = false;
	
	protected TRLabel titel = TRComponentManager.getLabel("GLÜCKWUNSCH!");
	protected TRLabel body = TRComponentManager.getLabel("body");
	protected TRTextButton button = new TamaryuButton("OK");
	protected Runnable action = null;
	
	public TamaDialog(String titel, String body, Runnable action){
		this.setImage(new TRImage("dialog3", "dialog3", "png", "/img", 0, 0, 0, fullW, fullH, fullW, fullH));
		this.setZ(100);
		
		this.action = action;
		
		this.titel.setText(titel);
		this.titel.setScale(1.5f);
		this.titel.setColor(Color.BLACK);
		this.titel.setAlignment(TRLabel.CENTER);
		this.titel.setPosition(100/1.5f, 650/1.5f, 101);
		this.titel.setSize(Math.round(600/1.5f), Math.round(this.titel.getHeight()/1.5f));
		this.addComponent(this.titel);
		
		this.body.setText(body);
		this.body.setColor(Color.BLACK);
		this.body.setAlignment(TRLabel.LEFT);
		this.body.setPosition(100, 650-this.titel.getHeight()*1.5f - this.body.getHeight()-30, 101);
		this.body.setSize(600, -1);
		this.addComponent(this.body);
		
		this.button.addClickAction(new Runnable(){
			@Override
			public void run() {
				up = true;
			}
		});
		this.button.setSize(600, this.button.getHeight());
		this.button.setPosition(100, 325, 101);
		this.button.setAlignment(TRLabel.CENTER);
		this.addComponent(button);
	}
	
	public void init(TRRenderContext c){
		GLCamera cam = (GLCamera) c.getScene().getCamera();
		float s = (cam.getWinHeigth()*cam.getScale())/this.fullH;
		this.setScale(s);
		int xoff = (int) (cam.getWinWidth()*cam.getScale() - this.width*this.getScale());
		this.setPosition((xoff/2)/this.getScale(), yUp, this.getPosition().z);
		super.init(c);
	}
	
	public void render(TRRenderContext c){
		if(!this.up){
			this.setY(Math.max(yDown, this.getPosition().y-25));
		}else{
			this.setY(Math.min(yUp, this.getPosition().y+25));
			if(this.getPosition().y == yUp){
				//ready = true;
				if(action != null)
					action.run();
			}
		}
		super.render(c);
	}
	
	public void resize(TRRenderContext rc, int w, int h){
		GLCamera cam = (GLCamera) rc.getScene().getCamera();
		float s = (cam.getWinHeigth()*cam.getScale())/this.fullH;
		this.setScale(s);
		int xoff = (int) (cam.getWinWidth()*cam.getScale() - this.width*this.getScale());
		this.setX((xoff/2)/this.getScale());
		//System.out.println("Div Scale: "+this.getAbsolutScale());
		super.resize(rc, w, h);
	}
}
