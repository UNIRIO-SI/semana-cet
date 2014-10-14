package aula4.model;

import aula4.server.PongServerListener;
import br.com.etyllica.layer.GeometricLayer;

public class Paddle extends GeometricLayer {

	private boolean upPressed = false;
	private boolean downPressed = false;
	
	private int speed = 10;
	
	public Paddle(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}
	
	public void update(int h) {
		if(upPressed) {
			setOffsetY(-speed);
			
			if(y <= PongServerListener.BORDER) {
				y = PongServerListener.BORDER;
			}
		}
		
		if(downPressed) {
			setOffsetY(speed);
			
			if(y+getH() >= h-PongServerListener.BORDER) {
				y = h-PongServerListener.BORDER-getH();
			}
		}
	}
		
}
