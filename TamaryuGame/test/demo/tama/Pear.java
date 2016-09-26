package demo.tama;

import com.tr.engine.grf.TRRenderPropertie;
import com.tr.engine.img.TRImage;
import com.tr.game.objects.DragableIcon;

public class Pear extends DragableIcon {
	
	public Pear(){
		this.setImage(new TRImage("pear1", "pear2_64", "png", "/img", 0, 0, 0, 64, 64, 64, 64));
		this.setPosition(100f,100f,10f);
	}

	@Override
	public void onDrag() {
		this.setZ(this.getPosition().z+1);
		this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 2, 0.9f,0.4f,0, 0));
		// TODO Auto-generated method stub
		//System.out.println("Start dragging!");
	}

	@Override
	public void onDrop() {
		this.setZ(this.getPosition().z-1);
		this.setRenderPropertie(new TRRenderPropertie(TRRenderPropertie.USE_OUTLINE, 0, 0,1,0, 0));
		// TODO Auto-generated method stub
		//System.out.println("Stop dragging!");
	}

}
