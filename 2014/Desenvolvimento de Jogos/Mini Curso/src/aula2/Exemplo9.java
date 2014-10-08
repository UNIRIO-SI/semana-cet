package aula2;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.layer.BufferedLayer;

public class Exemplo9 extends Application {

	private BufferedLayer beast;
	
	public Exemplo9(int w, int h) {
		super(w, h);
	}
	
	@Override
	public void load() {
		beast = new BufferedLayer(x, y, "xmen/beast.png");
		
		//beast.offsetNegativeRed(60); //Purple Like
		//beast.offsetNegativeGreen(200); //Green
		//beast.offsetNegativeBlue(60); //More Blue
		//beast.offsetRGB(0, 220, 200); //Very Cyan
		beast.offsetRGB(200, 10, -30); //Less Blue
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
				
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {

			double x = event.getX();
			double y = event.getY();
						
			double fx = (double)(x/w);
			double fy = (double)(y/h);
			
			beast.resetImage();
			beast.offsetRGB((int)(220*fx), (int)(220*fy), -40);
			
		}
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_RIGHT)) {
			beast.resetImage();
		}
		
		return GUIEvent.NONE;
	}
	
	@Override
	public void draw(Graphic g) {
		beast.draw(g);
	}

}
