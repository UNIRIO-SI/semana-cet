package aula1;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo3 extends Application {

	private ImageLayer background;
	private ImageLayer boo;
	private AnimatedLayer miniBoo;
	
	public Exemplo3(int w, int h) {
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
		
		miniBoo = new AnimatedLayer(120, 280, 16, 16, "miniboos.png");
		miniBoo.setSpeed(500); //in milliseconds
		miniBoo.setFrames(2); 
		miniBoo.setScale(2);
		
	}
	
	@Override
	public void update(long now) {
		miniBoo.animate(now);
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		miniBoo.draw(g);		
		boo.draw(g);		
	}

}
