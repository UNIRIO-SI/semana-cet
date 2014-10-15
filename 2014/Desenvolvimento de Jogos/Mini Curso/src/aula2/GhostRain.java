package aula2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;

public class GhostRain extends Application {

	private ImageLayer background;
	private ImageLayer boo;
		
	private List<AnimatedLayer> miniBoos = new ArrayList<AnimatedLayer>();
	
	private int speed = 2;
	
	//Controls
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
	
	private long delay = 300;
	private long lastCreation = 0;
	
	public GhostRain(int w, int h) {
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
		
		//boo.setAngle(50); //0~360
		boo.setOpacity(200); //0~255
		boo.setScale(2); //0~MAX_DOUBLE
		
		boo.centralize(this);
		
	}
	
	private void createMiniBoo() {
		
		int x = new Random().nextInt(w);
		
		AnimatedLayer miniBoo = new AnimatedLayer(x, -20, 16, 16, "miniboos.png");
		miniBoo.setSpeed(500);
		miniBoo.setFrames(2); 
		miniBoo.setScale(2);
		
		miniBoos.add(miniBoo);
	}
	
	@Override
	public void update(long now) {
		
		for(int i = miniBoos.size()-1; i > 0; i--) {
			
			AnimatedLayer miniBoo = miniBoos.get(i);
			
			miniBoo.animate(now);
			
			miniBoo.setOffsetY(speed);
			
			if(miniBoo.getY()>h)
				miniBoos.remove(miniBoo);
		}
		
		if(upPressed) {
			boo.setOffsetY(-speed);
		} else if(downPressed) {
			boo.setOffsetY(+speed);
		}
		
		if(rightPressed) {
			boo.setOffsetX(+speed);
			
		} else if(leftPressed) {
			boo.setOffsetX(-speed);			
		}
		
		if(now > lastCreation+delay) {
			lastCreation = now;
			createMiniBoo();
		}
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		
		boo.draw(g);
		
		for(AnimatedLayer miniBoo: miniBoos) {
			miniBoo.draw(g);	
		}
	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		if(event.isKeyDown(KeyEvent.TSK_UP_ARROW)) {
			upPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_UP_ARROW)) {
			upPressed = false;
		}
		
		if(event.isKeyDown(KeyEvent.TSK_DOWN_ARROW)) {
			downPressed = true;
		} else if(event.isKeyUp(KeyEvent.TSK_DOWN_ARROW)) {
			downPressed = false;			
		}
		
		if(event.isKeyDown(KeyEvent.TSK_RIGHT_ARROW)) {
			rightPressed = true;

			boo.setYImage(68);
			boo.setAngle(30);
						
		} else if(event.isKeyUp(KeyEvent.TSK_RIGHT_ARROW)) {
			rightPressed = false;			
		}
		
		if(event.isKeyDown(KeyEvent.TSK_LEFT_ARROW)) {
			leftPressed = true;
			
			boo.setYImage(0);
			boo.setAngle(-30);
		} else if(event.isKeyUp(KeyEvent.TSK_LEFT_ARROW)) {
			leftPressed = false;			
		}
		
		if(event.isKeyDown(KeyEvent.TSK_H)) {
			boo.setXImage(0);
		} else if(event.isKeyUp(KeyEvent.TSK_J)) {
			boo.setXImage(68);			
		}
		
		return GUIEvent.NONE;
	}

}
