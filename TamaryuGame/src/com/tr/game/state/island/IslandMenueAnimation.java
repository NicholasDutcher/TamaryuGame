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

	private final static int SCROLL_BODY_MIN_HEIGHT = SCROLL_OFFSET;
	private final static int SCROLL_BODY_MAX_HEIGHT = SCROLL_BODY_HEIGHT;
	private final static int MAX_FRAMES = 30;
	private int curHeight = SCROLL_BODY_MIN_HEIGHT;

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
		button.setText("Menü");
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
		scrollBody.setSize(SCROLL_WIDTH, SCROLL_BODY_MIN_HEIGHT);
		//scrollBody.setPosition(0, SCROLL_HEAD_HEIGHT - SCROLL_OFFSET, -1);
		createScrollAnimation();
		scrollBody.setZ(4);
	}

	private void createScrollAnimation() {
		ArrayList<TRFrame> frames = createFrames();
		TRAnimation ani = new TRAnimation();
		ani.setFixedFPS(30);

		// scroll down animation
		for (TRFrame f : frames) {
			ani.addFrame(f);
		}
		scrollBody.addAnimation("down", ani);

		// scroll up animation
		ani = new TRAnimation();
		ani.setFixedFPS(30);
		for (int i = frames.size()-1; i>=0; i--) {
			ani.addFrame(frames.get(i));
		}
		scrollBody.addAnimation("up", ani);

		// scroll default animation
		ani = new TRAnimation();
		ani.setFixedFPS(30);
		TRFrame f = new TRFrame();
		TRFrameAction a = new TRFrameAction();
		a.imgFlag = true;
		a.img = new TRImage("scrollBody", "menu_scroll_bottom", "png", "/img", 0, 0, 0, 300, 600, 300, 600);
		f.addAction(a);
		ani.setInitFram(f);
		scrollBody.addAnimation("default", ani);
		
		scrollBody.loadAnimation("default");
		scrollBody.start();

	}

	private ArrayList<TRFrame> createFrames() {
		ArrayList<TRFrame> frames = new ArrayList<TRFrame>();

		int x = (SCROLL_BODY_MAX_HEIGHT - SCROLL_BODY_MIN_HEIGHT) / MAX_FRAMES;
		int step = (SCROLL_BODY_MAX_HEIGHT - SCROLL_BODY_MIN_HEIGHT) / x;
		int last = (SCROLL_BODY_MAX_HEIGHT - step * x);
		last = step - (SCROLL_BODY_MAX_HEIGHT - last);

		for (int i = 0; i < x; i++) {
			TRFrame f = new TRFrame();
			TRFrameAction a = new TRFrameAction();

			//calculate height
			int h = 0;
			if(i+1 == x){
				h = last;
			}else{
				h = step;
			}
			
			a.imgFlag = true;
			a.img = new TRImage("scrollBody", "menu_scroll_bottom", "png", "/img", 0, 0, 0, 300, SCROLL_BODY_MIN_HEIGHT + h + (i-1)*step,
					300, 600);
			a.hFlag = true;
			a.h = SCROLL_BODY_MIN_HEIGHT + h + (i-1)*step;
			
			System.out.println("Frame "+i+": height = "+(i * (600 - 159) / x));

			f.addAction(a);
			frames.add(f);
		}

		return frames;
	}

	public int getScrollPosition() {
		return (int) scrollBody.getPosition().y;
	}
	
	public void scroll(){
		//scrollBody.setImage(new TRImage("scrollBody", "menu_scroll_bottom", "png", "/img", 0, 0, 0, 300, 600, 300, 600));
		if(scrollBody.getHeight() == SCROLL_BODY_MAX_HEIGHT){
			System.out.println("UP");
			scrollBody.loadAnimation("up");
		}else if(scrollBody.getHeight() == SCROLL_BODY_MIN_HEIGHT){
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
