package aula1;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo1 extends Application {

	private ImageLayer layer;
	
	public Exemplo1(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		layer = new ImageLayer("bg.png");
	}

	@Override
	public void draw(Graphic g) {
		layer.draw(g);
	}

}
