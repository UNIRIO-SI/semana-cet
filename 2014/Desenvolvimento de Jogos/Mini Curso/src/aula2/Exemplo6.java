package aula2;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo6 extends Application {

	private ImageLayer background;
	
	public Exemplo6(int w, int h) {
		super(w, h);
	}

	private boolean blink = false;

	public void load() {
		background = new ImageLayer("xmen/splash.png");

		updateAtFixedRate(1500);
	}

	public void timeUpdate(long now) {
		blink = !blink;		
	}

	public void draw(Graphic g) {
		background.draw(g);

		if(blink) {
			g.setFontSize(32f);
			g.drawStringShadowX(200, "Press Enter to Continue");
		}
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_ENTER)){
			nextApplication = new Exemplo7(w, h);
		}

		return null;		
	}

}
