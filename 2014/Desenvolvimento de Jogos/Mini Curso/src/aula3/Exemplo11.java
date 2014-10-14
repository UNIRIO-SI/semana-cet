package aula3;

import br.com.etyllica.animation.listener.OnAnimationFinishListener;
import br.com.etyllica.animation.scripts.HorizontalMovement;
import br.com.etyllica.animation.scripts.OrbitAnimation;
import br.com.etyllica.animation.scripts.RotateAnimation;
import br.com.etyllica.animation.scripts.complex.VerticalShakeScript;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.graphics.SVGColor;
import br.com.etyllica.effects.Effect;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo11 extends Application implements OnAnimationFinishListener {

	public Exemplo11(int w, int h) {
		super(w, h);
	}
	
	private ImageLayer background;
	
	private ImageLayer machine;
		
	private GeometricLayer wall;
	
	private Effect explosion;

	@Override
	public void load() {
		
		loadingInfo = "Loading images...";
				
		background = new ImageLayer("scene.png");
		machine = new ImageLayer(180,270,"machine.png");
		
		wall = new GeometricLayer(650, 0, 20, h);
		
		createAnimationScripts(machine, 0);
		
		explosion = new Effect(20, 20, 96, 96);
		explosion.setPath("explosion.png");
		explosion.setSpeed(100);
		explosion.setFrames(8);		
				
		loading = 50;
		loadingInfo = "Loading animations...";
						
	}
	
	private void createAnimationScripts(ImageLayer machine, int offsetAngle) {
		
		OrbitAnimation orbitAnimation = new OrbitAnimation(0,5000);
		orbitAnimation.setTarget(machine);
		orbitAnimation.setCenter(machine.getX(), machine.getY()+70);
		orbitAnimation.setInterval(offsetAngle, 360+offsetAngle);
		orbitAnimation.setRepeat(1);
		this.scene.addAnimation(orbitAnimation);
				
		RotateAnimation rotateAnimation = new RotateAnimation(0,5000);
		rotateAnimation.setTarget(machine);
		rotateAnimation.setInterval(offsetAngle, 360+offsetAngle);
		rotateAnimation.setRepeat(1);
		this.scene.addAnimation(rotateAnimation);
		
		HorizontalMovement horizontal = new HorizontalMovement(machine, 1500);
		horizontal.setInterval(machine.getX(), wall.getX());
		rotateAnimation.setNext(horizontal);
		
		horizontal.setListener(this);
		
	}

	@Override
	public void update(long now) {
		explosion.animate(now);
	}
	
	@Override
	public void draw(Graphic g) {
		background.draw(g);
		machine.draw(g);
						
		g.setAlpha(50);
		g.setColor(SVGColor.AQUA);
		g.fillRect(wall);
		g.setAlpha(100);

		explosion.draw(g);
	}

	@Override
	public void onAnimationFinish(long now) {
		machine.setVisible(false);
		
		explosion.setCoordinates(machine.getX()-20, machine.getY()-20);
		explosion.startEffect();	
		
		shakeCamera();
	}
	
	private void shakeCamera() {
		VerticalShakeScript shake = new VerticalShakeScript(background, 500);
		shake.setStrength(8);
		shake.repeat(3);
		
		this.scene.addAnimation(shake);
	}

}
