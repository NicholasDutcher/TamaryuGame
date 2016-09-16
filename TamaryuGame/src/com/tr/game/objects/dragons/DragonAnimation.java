package com.tr.game.objects.dragons;

import com.tr.engine.grf.IRenderable;
import com.tr.engine.grf.gl.TRGLAnimationView;
import com.tr.engine.input.TRDroparea;
import com.tr.game.objects.Dragon;

public class DragonAnimation extends TRGLAnimationView implements TRDroparea {
	protected int zIndex = 0; 
	protected Dragon ref = null;

	
	public DragonAnimation(int zIndex, Dragon ref){
		this.zIndex = zIndex;
		this.ref = ref;
	}

	@Override
	public boolean drop(IRenderable o) {
		if(ref != null)
			return ref.onDrop(o);
		return false;
	}

}
