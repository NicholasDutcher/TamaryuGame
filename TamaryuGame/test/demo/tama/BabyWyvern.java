package demo.tama;

import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.engine.img.TRImage;
import com.tr.engine.img.ani.TRAnimation;
import com.tr.engine.img.ani.TRFrame;
import com.tr.engine.img.ani.TRFrameAction;

public class BabyWyvern extends TRGLAnimationView{
	
	public BabyWyvern(int zIndex) {
		super();
		this.setZ(zIndex);
		this.setScale(0.8f);
		buildAni();
		createDefaultAni();
		this.loadDefault();
		this.start();
	}

	private void buildAni() {
		this.setSize(600, 600);
		TRGLAnimationView v = null;

		// add body
		v = new TRGLAnimationView();
		v.setName("body");
		v.setSize(356, 340);
		v.setZ(this.getPosition().z+1);
		this.addComponent(v);

		// add head
		v = new TRGLAnimationView();
		v.setName("head");
		v.setSize(294, 294);
		v.setZ(this.getPosition().z+2);
		this.addComponent(v);

		// add right leg
		v = new TRGLAnimationView();
		v.setName("rleg");
		v.setSize(260, 336);
		v.setZ(this.getPosition().z+2);
		this.addComponent(v);

		// add left leg
		v = new TRGLAnimationView();
		v.setName("lleg");
		v.setSize(260, 336);
		v.setZ(this.getPosition().z+4);
		this.addComponent(v);

		// add tail
		v = new TRGLAnimationView();
		v.setName("tail");
		v.setSize(230, 210);
		v.setPosition(200, 180, 0);
		v.setZ(this.getPosition().z+2);
		this.addComponent(v);

		// add eye
		v = new TRGLAnimationView();
		v.setName("eyes");
		v.setSize(216, 120);
		v.setPosition(50, 80, 0);
		v.setZ(this.getPosition().z+3);
		this.getComponentByName("head").addComponent(v);
	}

	private void createDefaultAni() {
		TRAnimation ani = new TRAnimation();
		ani.setLoop(true);
		TRFrame frame = new TRFrame();

		// set default body image
		TRFrameAction action = new TRFrameAction();
		action.path = "body";
		action.posXFlag = true;
		action.posX = 100;
		action.posYFlag = true;
		action.posY = 220;
		action.imgFlag = true;
		action.img = new TRImage("bodyDefault", "wyvern_baby_body_356x340", "png", "/img", 0, 0, 0, 356, 340, 356, 340);
		frame.addAction(action);

		// set default head image
		action = new TRFrameAction();
		action.posYFlag = true;
		action.posY = 506;
		action.path = "head";
		action.imgFlag = true;
		action.img = new TRImage("headDefault", "wyvern_baby_head_294x294", "png", "/img", 0, 0, 0, 294, 294, 294, 294);
		frame.addAction(action);

		// set default left leg image
		action = new TRFrameAction();
		action.path = "lleg";
		action.imgFlag = true;
		action.img = new TRImage("llegDefault", "wyvern_baby_leg_260x336", "png", "/img", 0, 0, 0, 260, 336, 1560, 336);
		frame.addAction(action);

		// set default right leg image
		action = new TRFrameAction();
		action.path = "rleg";
		action.imgFlag = true;
		action.img = new TRImage("llegDefault", "wyvern_baby_leg_260x336", "png", "/img", 0, 0, 0, 260, 336, 1560, 336);
		frame.addAction(action);

		// set default tail image
		action = new TRFrameAction();
		action.path = "tail";
		action.imgFlag = true;
		action.img = new TRImage("tailDefault", "wyvern_baby_tail_230x210", "png", "/img", 0, 0, 0, 230, 210, 1150, 210);
		frame.addAction(action);

		// set default eyes image
		action = new TRFrameAction();
		action.path = "head.eyes";
		action.imgFlag = true;
		action.img = new TRImage("eyesDefault", "wyvern_baby_eye_216x120", "png", "/img", 0, 0, 0, 216, 120, 432, 120);
		frame.addAction(action);

		ani.setInitFram(frame);
		this.addAnimation("default", ani);
	}


}