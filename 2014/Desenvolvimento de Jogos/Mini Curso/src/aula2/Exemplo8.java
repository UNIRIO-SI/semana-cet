package aula2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;
import br.com.tide.input.controller.Controller;
import br.com.tide.input.controller.EasyController;

import com.marvel.quest.enemy.Enemy;
import com.marvel.quest.enemy.StrongMan;
import com.marvel.quest.hero.Beast;
import com.marvel.quest.hero.Gambit;
import com.marvel.quest.hero.Hero;
import com.marvel.quest.hero.MarvelCharacter;

public class Exemplo8 extends Application {

	public Exemplo8(int w, int h) {
		super(w, h);
	}

	private List<MarvelCharacter> characters = new ArrayList<MarvelCharacter>();
	
	private ImageLayer background;
	
	private Hero hero;
		
	private Controller easyController;
		
	private Enemy strongMan;

	@Override
	public void load() {
		
		background = new ImageLayer("xmen/sor3.png");		
		
		if(Exemplo7.BEAST.equals(session.getAsString(Exemplo7.CHAR_PARAM))) {
			hero = new Beast(40, 200);
		} else if(Exemplo7.GAMBIT.equals(session.getAsString(Exemplo7.CHAR_PARAM))) {
			hero = new Gambit(40, 200);
		}
				
		easyController = new EasyController(hero);
		
		characters.add(hero);
				
		loadEnemy();
				
		updateAtFixedRate(30);
		
		loading = 100;
	}
	
	private void loadEnemy() {
		
		strongMan = new StrongMan(530, 200);
		
		strongMan.setTarget(hero);
		
		characters.add(strongMan);
	}
	
	@Override
	public void timeUpdate(long now){
		
		for(MarvelCharacter player: characters){
			player.update(now);
		}
				
		Collections.sort(characters);		
	}
	
	@Override
	public void draw(Graphic g) {
		
		background.draw(g);
		
		for(MarvelCharacter character: characters) {
			character.draw(g);
		}

	}
	
	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		
		easyController.handleEvent(event);
				
		return null;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

}
