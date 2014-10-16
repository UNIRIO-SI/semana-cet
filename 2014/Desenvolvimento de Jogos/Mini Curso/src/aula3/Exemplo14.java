package aula3;

import java.awt.Color;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.effects.light.LightSource;
import br.com.etyllica.effects.light.ShadowLayer;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo14 extends Application{
	
	private ShadowLayer shadow;
	
	private LightSource source;
		
	private ImageLayer background;
	
	private ImageLayer candle;

	public Exemplo14(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		loading = 10;
		
		background = new ImageLayer("dungeon/dungeon.png");
		
		candle = new ImageLayer(0, 0, "dungeon/candle.png");
		candle.centralizeX(this);
		
		Color shadowColor = new Color(100,100,100,100);
		
		shadow = new ShadowLayer(x, y, w, h);
		shadow.setColor(shadowColor);
		
		source = new LightSource(-12, 104, 200);
		source.setCenterColor(new Color(0xFF,0xFF,0xFF,10));
		source.setBorderColor(shadowColor);
		
		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		
		background.draw(g);
		candle.draw(g);
		
		shadow.drawLights(g, source);
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
			System.out.println("x: "+event.getX());
			System.out.println("y: "+event.getY());
		}
		
		return null;
	}

}
