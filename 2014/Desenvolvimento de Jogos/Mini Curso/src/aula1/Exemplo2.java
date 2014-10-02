package aula1;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo2 extends Application {

	private ImageLayer background;
	private ImageLayer boo;
	
	public Exemplo2(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		background = new ImageLayer("ghost_house.png");
		
		boo = new ImageLayer("boo.png");
		
		//Crop Image
		boo.setW(68);
		boo.setH(68);
		
		//Move Image Viewport
		boo.setXImage(68);
		
		boo.setAngle(50); //0~360
		boo.setOpacity(200); //0~255
		boo.setScale(2); //0~MAX_DOUBLE
		
		boo.centralize(this);
		
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		boo.draw(g);
	}

}
