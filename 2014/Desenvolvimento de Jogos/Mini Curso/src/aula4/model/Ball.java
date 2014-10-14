package aula4.model;

import br.com.etyllica.layer.GeometricLayer;

public class Ball extends GeometricLayer {
	
	private double dx = 0;
	private double dy = 0;
	
	private double realX = 0;
	private double realY = 0;
	
	private int speed = 4;
	
	public Ball(int x, int y, int w, int h) {
		super(x, y, w, h);
		
		realX = x;
		realY = y;		
	}
	
	public void setAngle(double angle) {
		dx = Math.sin(Math.toRadians(angle)) * speed;
		dy = Math.cos(Math.toRadians(angle)) * speed;
	}
	
	@Override
	public void setCoordinates(int x, int y) {
		super.setCoordinates(x, y);
		
		this.realX = x;
		this.realY = y;
	}
	
	public void update() {
				
		realX += dx*speed;
		realY += dy*speed;
		
		x = (int)realX;
		y = (int)realY;		
	}

	public void alternateDy() {
		dy = -dy;
	}
	
	
		
}
