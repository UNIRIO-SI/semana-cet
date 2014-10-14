package aula1;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.AnimatedLayer;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo4 extends Application {

	private ImageLayer background;
	private ImageLayer boo;
	private AnimatedLayer miniBoo;
	
	private int speed = 2;
	
	//Controls
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
	public Exemplo4(int w, int h) {
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
		
		miniBoo = new AnimatedLayer(120, 280, 16, 16, "miniboos.png");
		miniBoo.setSpeed(500); //in milliseconds
		miniBoo.setFrames(2); 
		miniBoo.setScale(2);
		
	}
	
	@Override
	public void update(long now) {
		miniBoo.animate(now);
		
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
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		miniBoo.draw(g);
		boo.draw(g);
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
