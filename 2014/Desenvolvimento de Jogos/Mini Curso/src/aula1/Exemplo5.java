package aula1;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo5 extends Application {

	private ImageLayer background;
	
	private ImageLayer boo;
	private ImageLayer anotherBoo;
	
	private AnimatedLayer miniBoo;
	
	public Exemplo5(int w, int h) {
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
		
		anotherBoo = new ImageLayer("boo.png");
		anotherBoo.setW(68);
		anotherBoo.setH(68);
		anotherBoo.setScale(boo.getScaleX());
		
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
		anotherBoo.draw(g);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		anotherBoo.setCoordinates(event.getX(), event.getY());
		
		return GUIEvent.NONE;
	}

}
