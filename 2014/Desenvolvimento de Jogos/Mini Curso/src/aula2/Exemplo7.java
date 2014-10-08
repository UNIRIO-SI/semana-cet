package aula2;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.core.input.mouse.MouseButton;
import br.com.etyllica.layer.ImageLayer;

public class Exemplo7 extends Application {

	public static final String BEAST = "beast";
	public static final String GAMBIT = "gambit";

	public static final String CHAR_PARAM = "char";

	private ImageLayer background;

	private ImageLayer beast;

	private ImageLayer gambit;

	private ImageLayer selectionMark;

	private boolean selected = false;

	public Exemplo7(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		background = new ImageLayer("xmen/splash.png");

		beast = new ImageLayer(180, 200, 64, 104, 0, 0, "xmen/selection.png");
		beast.centralizeY(0, h);
		beast.setScale(2);

		gambit = new ImageLayer(310, 200, 64, 104, 65, 0, "xmen/selection.png");
		gambit.centralizeY(0, h);
		gambit.setScale(2);

		selectionMark = new ImageLayer("xmen/mark.png");
		selectionMark.setScale(2);

		loading = 100;
	}

	@Override
	public void draw(Graphic g) {
		background.draw(g);
		
		g.setFontSize(32f);
		g.drawStringShadowX(30, "Select your character");
		
		beast.draw(g);
		gambit.draw(g);

		if(selected)
			selectionMark.draw(g);
	}

	public GUIEvent updateMouse(PointerEvent event) {

		if(beast.onMouse(event)) {
			overChar(beast);

			if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
				selectChar(BEAST);				
			}

		} else if(gambit.onMouse(event)) {
			overChar(gambit);

			if(event.isButtonDown(MouseButton.MOUSE_BUTTON_LEFT)) {
				selectChar(GAMBIT);
			}

		} else {
			selected = false;
		}

		return GUIEvent.NONE;
	}

	private void overChar(ImageLayer layer) {
		selectionMark.setCoordinates(layer.getX(), layer.getY());
		selected = true;
	}

	private void selectChar(String charName) {
		session.put("char", charName);
		nextApplication = new Exemplo8(w, h);
	}

}
