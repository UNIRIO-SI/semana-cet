package aula3;

import java.awt.geom.AffineTransform;

import br.com.etyllica.animation.scripts.HorizontalMovement;
import br.com.etyllica.cinematics.Camera;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

import com.marvel.quest.Stage;

public class Exemplo12 extends Stage {

	private ImageLayer background;
	
	private Camera camera;
	
	public Exemplo12(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
				
		background = new ImageLayer("xmen/sor3.png");
		
		camera = new Camera(0, 0, w, h);
		
		HorizontalMovement cameraMovement = new HorizontalMovement(camera, 10000);
		cameraMovement.setInterval(0, -500);
		scene.addAnimation(cameraMovement);
		
		loading = 100;
	}
		
	@Override
	public void draw(Graphic g) {
		
		g.setTransform(AffineTransform.getTranslateInstance(camera.getX(), camera.getY()));
		
		background.draw(g);
		
		g.resetTransform();		
	}

}
