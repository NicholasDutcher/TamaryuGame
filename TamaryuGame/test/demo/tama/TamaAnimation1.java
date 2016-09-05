package demo.tama;

import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.engine.img.TRImage;
import com.tr.engine.img.ani.TRAnimation;
import com.tr.engine.img.ani.TRFrame;
import com.tr.engine.img.ani.TRFrameAction;

public class TamaAnimation1 extends TRGLAnimationView {

	public TamaAnimation1() {
		super();
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
		v.setSize(600, 600);
		v.setZ(-30);
		this.addComponent(v);

		// add head
		v = new TRGLAnimationView();
		v.setName("head");
		v.setSize(600, 600);
		v.setZ(-20);
		this.addComponent(v);

		// add left arm
		v = new TRGLAnimationView();
		v.setName("larm");
		v.setSize(600, 600);
		v.setZ(-15);
		this.addComponent(v);

		// add left arm
		v = new TRGLAnimationView();
		v.setName("rarm");
		v.setSize(600, 600);
		v.setZ(-15);
		this.addComponent(v);

		// add right leg
		v = new TRGLAnimationView();
		v.setName("rleg");
		v.setSize(600, 600);
		v.setZ(-10);
		this.addComponent(v);

		// add left leg
		v = new TRGLAnimationView();
		v.setName("lleg");
		v.setSize(600, 600);
		v.setZ(-35);
		this.addComponent(v);

		// add tail
		v = new TRGLAnimationView();
		v.setName("tail");
		v.setSize(400, 420);
		v.setPosition(200, 180, 0);
		v.setZ(-40);
		this.addComponent(v);

		// add horn
		v = new TRGLAnimationView();
		v.setName("horn");
		v.setSize(600, 600);
		v.setZ(-15);
		this.getComponentByName("head").addComponent(v);

		// add mouth
		v = new TRGLAnimationView();
		v.setName("mouth");
		v.setSize(600, 600);
		v.setZ(-15);
		this.getComponentByName("head").addComponent(v);

		// add eye
		v = new TRGLAnimationView();
		v.setName("eyes");
		v.setSize(150, 250);
		v.setPosition(190, 100, 0);
		v.setZ(-15);
		this.getComponentByName("head").addComponent(v);
	}

	private void createDefaultAni() {
		TRAnimation ani = new TRAnimation();
		ani.setLoop(true);
		TRFrame frame = new TRFrame();

		// set default body image
		TRFrameAction action = new TRFrameAction();
		action.path = "body";
		action.imgFlag = true;
		action.img = new TRImage("bodyDefault", "body_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default head image
		action = new TRFrameAction();
		action.path = "head";
		action.imgFlag = true;
		action.img = new TRImage("headDefault", "head_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default left arm image
		action = new TRFrameAction();
		action.path = "larm";
		action.imgFlag = true;
		action.img = new TRImage("larmDefault", "arm_l_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default right arm image
		action = new TRFrameAction();
		action.path = "rarm";
		action.imgFlag = true;
		action.img = new TRImage("rarmDefault", "arm_r_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default left leg image
		action = new TRFrameAction();
		action.path = "lleg";
		action.imgFlag = true;
		action.img = new TRImage("llegDefault", "leg_l_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default right leg image
		action = new TRFrameAction();
		action.path = "rleg";
		action.imgFlag = true;
		action.img = new TRImage("rlegDefault", "leg_r_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default tail image
		action = new TRFrameAction();
		action.path = "tail";
		action.imgFlag = true;
		action.img = new TRImage("tailDefault", "sprite01_tail_200x180", "png", "/img", 0, 0, 0, 200, 180, 1000, 180);
		frame.addAction(action);

		// set default horn image
		action = new TRFrameAction();
		action.path = "head.horn";
		action.imgFlag = true;
		action.img = new TRImage("hornDefault", "horn_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default mouth image
		action = new TRFrameAction();
		action.path = "head.mouth";
		action.imgFlag = true;
		action.img = new TRImage("mouthDefault", "mouth_1", "png", "/img", 0, 0, 0, 600, 600, 600, 600);
		frame.addAction(action);

		// set default eyes image
		action = new TRFrameAction();
		action.path = "head.eyes";
		action.imgFlag = true;
		action.img = new TRImage("eyesDefault", "sprite01_eyes_190x100", "png", "/img", 0, 0, 0, 190, 180, 380, 180);
		frame.addAction(action);

		ani.setInitFram(frame);
		this.addAnimation("default", ani);
	}

}
