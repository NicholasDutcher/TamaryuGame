package com.tr.game.objects;

import com.tr.engine.components.TRTextButton;
import com.tr.engine.components.gl.TRGLTextButton;
import com.tr.engine.grf.Color;
import com.tr.engine.sound.AudioMaster;

public class TamaryuButton extends TRGLTextButton {
	private TamaryuButton that = this;

	public TamaryuButton(String name){
		super(name);
		this.setFont("Arial", true);
		that.setColor(new Color(0/255f, 0/255f, 0/255f, 0f));
		
		this.addStateChangeAction(TRTextButton.MOUSE_ENTER_ACTION, new Runnable() {
			@Override
			public void run() {
				that.setColor(new Color(255f/255f, 153f/255f, 18f/255f, 0f));
				AudioMaster.playSource(0);
			}
		});

		this.addStateChangeAction(TRTextButton.MOUSE_LEAVE_ACTION, new Runnable() {
			@Override
			public void run() {
				that.setColor(new Color(0/255f, 0/255f, 0/255f, 0f));
				AudioMaster.stopSource(0);
			}
		});

		this.addStateChangeAction(TRTextButton.MOUSE_DOWN_ACTION, new Runnable() {
			@Override
			public void run() {
				that.setColor(new Color(205f/255f, 102f/255f, 9f/255f, 0f));
			}
		});

		this.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {
			@Override
			public void run() {
				that.setColor(new Color(255f/255f, 153f/255f, 18f/255f, 0f));
			}
		});
	}
	
	public TamaryuButton(){
		this("");
	}
}
