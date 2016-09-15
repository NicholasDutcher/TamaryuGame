package demo.tama;

import com.tr.engine.img.TRImage;
import com.tr.game.objects.DragableIcon;

public class Pear extends DragableIcon {
	
	public Pear(){
		this.setImage(new TRImage("pear1", "pear_32", "png", "/img", 0, 0, 0, 32, 32, 32, 32));
		this.setPosition(100f,100f,10f);
	}

	@Override
	public void onDrag() {
		this.setZ(this.getPosition().z+1);
		// TODO Auto-generated method stub
		System.out.println("Start dragging!");
	}

	@Override
	public void onDrop() {
		this.setZ(this.getPosition().z-1);
		// TODO Auto-generated method stub
		System.out.println("Stop dragging!");
	}

}
