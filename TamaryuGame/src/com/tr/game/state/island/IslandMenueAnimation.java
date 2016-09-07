package com.tr.game.state.island;

import java.util.ArrayList;

import com.tr.engine.components.TRComponentManager;
import com.tr.engine.components.TRTextButton;
import com.tr.engine.components.gl.TRGLLabel;
import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.engine.grf.gl.TRGLImageView;
import com.tr.engine.img.TRImage;
import com.tr.engine.img.ani.TRAnimation;
import com.tr.engine.img.ani.TRFrame;
import com.tr.engine.img.ani.TRFrameAction;
import com.tr.engine.input.TRDragable;
import com.tr.util.LanguageTranslator;

public class IslandMenueAnimation extends TRGLImageView {

	protected TRGLAnimationView scrollBody = new TRGLAnimationView();
	protected TRTextButton button = TRComponentManager.getTxtButton();

	private final static int SCROLL_WIDTH = 300;
	private final static int SCROLL_HEAD_HEIGHT = 159;
	private final static int SCROLL_OFFSET = 170;
	private final static int SCROLL_BODY_HEIGHT = 600;

	private final static int SCROLL_BODY_MIN_HEIGHT = SCROLL_HEAD_HEIGHT;
	private final static int SCROLL_BODY_MAX_HEIGHT = SCROLL_BODY_HEIGHT;
	private final static int SCROLL_BODY_OFFSET = SCROLL_HEAD_HEIGHT/2;
	private final static int MAX_FRAMES = 16;

	public IslandMenueAnimation() {
		this.setFixedPosition(FIXED_POS_TOP_RIGHT);
		this.setSize(SCROLL_WIDTH, SCROLL_HEAD_HEIGHT);
		this.setZ(5);
		init();
	}

	public void init() {
		createScrollButton();
		createScrollBody();
		initButton();

		this.addComponent(scrollBody);
		scrollBody.start();
	}

	public void initButton() {

		// button.setText(LanguageTranslator.getString("menustring"));
		button.setText("Men�");
		button.setSize(SCROLL_WIDTH, SCROLL_HEAD_HEIGHT);

		button.setZ(this.getPosition().z + 1);
		button.setAlignment(TRGLLabel.CENTER);
		this.addComponent(button);
	}

	public TRTextButton getButton() {
		return button;
	}

	private void createScrollButton() {
		this.setImage(new TRImage("scrollHead", "menu_scroll_top", "png", "/img", 0, 0, 0, 300, SCROLL_HEAD_HEIGHT, 300,
				SCROLL_HEAD_HEIGHT));

	}

	private void createScrollBody() {
		/*scrollBody
				.setImage(new TRImage("scrollBody", "menu_scroll_bottom", "png", "/img", 0, 0, 0, 300, 600, 300, 600));*/
		scrollBody.setSize(SCROLL_WIDTH, SCROLL_BODY_MIN_HEIGHT-SCROLL_BODY_OFFSET);
		//scrollBody.setPosition(0, SCROLL_HEAD_HEIGHT - SCROLL_OFFSET, -1);
		createScrollAnimation();
		scrollBody.setZ(4);
	}

	private void createScrollAnimation() {
		ArrayList<TRFrame> frames = createFrames();
		TRAnimation ani = new TRAnimation();
		ani.setFixedFPS(30);
		ani.setLoop(false);

		// scroll down animation
		for (TRFrame f : frames) {
			ani.addFrame(f);
		}
		scrollBody.addAnimation("down", ani);

		// scroll up animation
		ani = new TRAnimation();
		ani.setFixedFPS(30);
		ani.setLoop(false);
		for (int i = frames.size()-1; i>=0; i--) {
			ani.addFrame(frames.get(i));
		}
		scrollBody.addAnimation("up", ani);

		// scroll default animation
		ani = new TRAnimation();
		ani.setFixedFPS(30);
		ani.setLoop(false);
		ani.setInitFram(frames.get(0));
		scrollBody.addAnimation("default", ani);
		
		scrollBody.loadAnimation("default");
		scrollBody.start();

	}

	private ArrayList<TRFrame> createFrames() {
		ArrayList<TRFrame> frames = new ArrayList<TRFrame>();

		int step = (SCROLL_BODY_MAX_HEIGHT - SCROLL_BODY_MIN_HEIGHT) / MAX_FRAMES;
		int hStep = (int) (SCROLL_BODY_OFFSET / (MAX_FRAMES*0.75f));

		for (int i = 0; i < MAX_FRAMES; i++) {
			TRFrame f = new TRFrame();
			TRFrameAction a = new TRFrameAction();

			//calculate height
			int h = 0;
			if(i+1 == MAX_FRAMES){
				h = SCROLL_BODY_MAX_HEIGHT;
			}else{
				h = SCROLL_BODY_MIN_HEIGHT  + i*step;
			}
			
			a.imgFlag = true;
			a.img = new TRImage("scrollBody", "menu_scroll_bottom", "png", "/img", 0, 0, 0, 300, h-SCROLL_BODY_OFFSET,
					300, 600);
			a.hFlag = true;
			a.h =h - SCROLL_BODY_OFFSET;
			a.posYFlag = true;
			a.posY = SCROLL_BODY_MIN_HEIGHT - h;
			
			System.out.println("Frame "+i+": ah = "+a.h+"; Tex h = " + h);

			f.addAction(a);
			frames.add(f);
		}

		return frames;
	}

	public int getScrollPosition() {
		return (int) scrollBody.getPosition().y;
	}
	
	public void scroll(){
		System.out.println("Start Animation! ("+scrollBody.getHeight()+")");
		//scrollBody.loadAnimation("down");
		//scrollBody.setImage(new TRImage("scrollBody", "menu_scroll_bottom", "png", "/img", 0, 0, 0, 300, 600, 300, 600));
		if(scrollBody.getHeight() == (SCROLL_BODY_MAX_HEIGHT-SCROLL_BODY_OFFSET)){
			System.out.println("UP");
			scrollBody.loadAnimation("up");
		}else if(scrollBody.getHeight() == (SCROLL_BODY_MIN_HEIGHT-SCROLL_BODY_OFFSET)){
			System.out.println("DOWN");
			scrollBody.loadAnimation("down");
		}
	}

	public int getScrollMinPos() {
		return SCROLL_HEAD_HEIGHT - SCROLL_OFFSET;
	}

	public int getScrollMaxPos() {
		return -1 * (SCROLL_BODY_HEIGHT - SCROLL_HEAD_HEIGHT);
	}

	public void setScrollPosition(int v) {
		scrollBody.setY(v);
	}

}
