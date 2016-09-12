package com.tr.game.state.island;

import com.tr.engine.components.TRTextButton;
import com.tr.engine.gameobject.Decoration;
import com.tr.engine.grf.IRenderable;

public class IslandMenue extends Decoration {
	private boolean scrolling = false;
	private int scrollTarget = 0;

	private IslandMenueAnimation ani = new IslandMenueAnimation();

	public IslandMenue() {
		this(0, 0, null);
		this.setImage(ani);

		TRTextButton b = ani.getButton();
		b.addStateChangeAction(TRTextButton.MOUSE_UP_ACTION, new Runnable() {

			@Override
			public void run() {
				startScroll();
			}
		});
	}

	public IslandMenue(int x, int y, IRenderable image) {
		super(0, 0, image);
	}

	public TRTextButton getButton() {
		return this.ani.getButton();
	}

	private void startScroll() {
		ani.scroll();
		/*
		 * if(!scrolling){
		 * //System.out.println("Scroll Head Z: "+ani.getPosition().
		 * z+"; Scroll Body Z: "+ani.getButton().getPosition().z);
		 * if(ani.getScrollPosition() == ani.getScrollMinPos()){ scrollTarget =
		 * ani.getScrollMaxPos(); }else{ scrollTarget = ani.getScrollMinPos(); }
		 * scrolling = true; }
		 */
	}

	private void updateScroll() {
		if (scrolling) {
			int pos = ani.getScrollPosition();
			if (scrollTarget > pos) {
				ani.setScrollPosition(Math.min(scrollTarget, pos + 50));
			} else {
				ani.setScrollPosition(Math.max(scrollTarget, pos - 50));
			}

			if (scrollTarget == ani.getScrollPosition()) {
				// System.out.println("Body Pos: "+ani.getScrollPosition());
				scrolling = false;
			}
		}
	}

	public void update(long i) {
		super.update(i);
		updateScroll();
	}

}
