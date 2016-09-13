package demo.tama;

import com.jogamp.opengl.util.packrect.Rect;
import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.engine.img.TRImage;
import com.tr.engine.img.ani.TRAnimation;
import com.tr.engine.img.ani.TRFrame;
import com.tr.engine.img.ani.TRFrameAction;
import com.tr.engine.input.ITRMouseListener;
import com.tr.engine.input.TRDroparea;
import com.tr.engine.input.TRMouseEvent;

public class BabyWyvern extends TRGLAnimationView{

	public BabyWyvern(int zIndex) {
		super();
		this.setZ(zIndex);
		this.setScale(0.5f);
		buildAni();
		createDefaultAni();
		createLookLeftAni();
		createLookRightAni();
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
		v.setZ(this.getPosition().z + 1);
		this.addComponent(v);

		// add head
		v = new TRGLAnimationView();
		v.setName("head");
		v.setSize(294, 294);
		v.setZ(this.getPosition().z + 2);
		this.addComponent(v);

		// add right leg
		v = new TRGLAnimationView();
		v.setName("rleg");
		v.setSize(260, 336);
		v.setPosition(255, 100, 0);
		v.setZ(this.getPosition().z);
		this.addComponent(v);

		// add left leg
		v = new TRGLAnimationView();
		v.setName("lleg");
		v.setSize(260, 336);
		v.setPosition(255, 100, 0);
		v.setZ(this.getPosition().z + 4);
		this.addComponent(v);

		// add tail
		v = new TRGLAnimationView();
		v.setName("tail");
		v.setSize(230, 210);
		v.setPosition(330, 290, 0);
		v.setZ(this.getPosition().z);
		this.addComponent(v);

		// add wing right
		v = new TRGLAnimationView();
		v.setName("rwing");
		v.setSize(220, 220);
		v.setPosition(264, 435, 0);
		v.setZ(this.getPosition().z);
		this.addComponent(v);

		// add wing left
		v = new TRGLAnimationView();
		v.setName("lwing");
		v.setSize(220, 220);
		v.setPosition(264, 435, 0);
		v.setZ(this.getPosition().z+4);
		this.addComponent(v);

		// add eye
		v = new TRGLAnimationView();
		v.setName("eyes");
		v.setSize(100, 120);
		v.setPosition(22, 77, 0);
		v.setZ(this.getPosition().z + 3);
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
		action.posY = 250;
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
		action.img = new TRImage("tailDefault", "wyvern_baby_tail_230x210", "png", "/img", 0, 0, 0, 230, 210, 1150,
				210);
		frame.addAction(action);

		// set default right wing image
		action = new TRFrameAction();
		action.path = "rwing";
		action.imgFlag = true;
		action.img = new TRImage("rwingDefault", "wyvern_baby_wings_220x220", "png", "/img", 0, 0, 0, 220, 220, 660,
				220);
		frame.addAction(action);

		// set default left wing image
		action = new TRFrameAction();
		action.path = "lwing";
		action.imgFlag = true;
		action.img = new TRImage("lwingDefault", "wyvern_baby_wings_220x220", "png", "/img", 0, 0, 0, 220, 220, 660,
				220);
		frame.addAction(action);

		// set default eyes image
		action = new TRFrameAction();
		action.path = "head.eyes";
		action.imgFlag = true;
		action.img = new TRImage("eyesDefault", "wyvern_baby_eye_216x120", "png", "/img", 0, 0, 0, 210, 120, 432, 120);
		frame.addAction(action);

		ani.setInitFram(frame);
		this.addAnimation("default", ani);
	}
	
	private void createLookLeftAni(){
		TRAnimation ani = new TRAnimation();
		ani.setLoop(true);
		TRFrame frame = new TRFrame();

		TRFrameAction action = new TRFrameAction();
		action.path = "this";
		action.rotYFlag = true;
		action.rotY = 0;
		frame.addAction(action);
		
		ani.setInitFram(frame);
		this.addAnimation("lookLeft", ani);
	}
	
	private void createLookRightAni(){
		TRAnimation ani = new TRAnimation();
		ani.setLoop(true);
		TRFrame frame = new TRFrame();

		TRFrameAction action = new TRFrameAction();
		action.path = "this";
		action.rotYFlag = true;
		action.rotY = 180;
		frame.addAction(action);
		
		ani.setInitFram(frame);
		this.addAnimation("lookRight", ani);
	}

}