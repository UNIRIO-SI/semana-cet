package aula4.client;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

import aula4.model.Ball;
import aula4.server.PongServerListener;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.KeyState;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.GeometricLayer;
import br.com.etyllica.layer.ImageLayer;
import br.com.etyllica.network.realtime.ClientActionListener;
import br.com.etyllica.network.realtime.model.KeyAction;
import br.com.etyllica.network.realtime.model.Message;
import examples.action.ActionServerApplication;
import examples.action.adapter.kryo.KryoActionClient;
import examples.action.model.ActionClient;
import examples.action.model.State;

public class PongApplication extends Application implements ClientActionListener<State> {

	private ActionClient client;

	private Map<Integer, GeometricLayer> players = new LinkedHashMap<Integer, GeometricLayer>();

	private Ball ball;
	
	private ImageLayer background;
	
	//Message Stuff
	private int sender = -1;
	private String messageText = "";

	public PongApplication(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {

		String host = "127.0.0.1";

		client = new KryoActionClient<State>(host, ActionServerApplication.ACTION_TCP_PORT, ActionServerApplication.ACTION_UDP_PORT, this);
		client.init();
		client.prepare();

		try {
			client.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ball = new Ball(0, 0, 20, 20);
		
		background = new ImageLayer("ice.png");

		loading = 100;
	}

	public void update(long now) {
		
	}

	@Override
	public void draw(Graphic g) {

		/*g.setColor(Color.CYAN);
		g.fillRect(0, 0, w, h);*/
		background.draw(g);

		g.setColor(Color.BLACK);

		for(GeometricLayer player: players.values()) {
			g.fillRect(player);
		}
		
		g.fillRect(ball);

	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		if(event.isKeyDown(KeyEvent.TSK_1)) {
			client.sendMessage("Tah malz, hein!");
		}

		else if(event.isKeyDown(KeyEvent.TSK_2)) {
			client.sendMessage("Vc tah roubando!");
		}

		else if(event.isKeyDown(KeyEvent.TSK_3)) {
			client.sendMessage("No pc eh mole, partiu Ping Pong!?");
		}
		
		else if(event.isKeyDown(KeyEvent.TSK_4)) {
			client.sendMessage("Tava olhando pro lado, essa nao valeu!");
		}

		//Ignore Repeat Keys
		if(event.getState() != KeyState.TYPED)
			client.sendKeyAction(new KeyAction(event.getKey(), event.getState()));

		return GUIEvent.NONE;
	}

	@Override
	public void updateStates(State[] states) {

		for(State state: states) {

			int id = state.id;

			GeometricLayer player = null;
						
			if(id == PongServerListener.BALL_ID) {
				player = ball;
			} else {
				player = getPlayer(id);
			}

			if(player != null)
				player.setCoordinates(state.x, state.y);
		}
	}

	@Override
	public void receiveMessage(Message message) {
		sender = message.sender;
		messageText = message.text;
	}

	public GeometricLayer getPlayer(int id) {
				
		GeometricLayer player = players.get(id);
		
		if(player == null) {
			if(players.size() == 0)
				player = createFirstPlayer(id);
			else if (players.size() == 1)
				player = createSecondPlayer(id);	
		}
		
		return player;		
	}

	private GeometricLayer createFirstPlayer(int id) {
		return players.put(id, new GeometricLayer(20, h/2-120/2, 20, 120));
	}

	private GeometricLayer createSecondPlayer(int id) {
		return players.put(id, new GeometricLayer(w-20-20, h/2-120/2, 20, 120));
	}

	@Override
	public void playerLeft(int id) {
		players.remove(id);
	}

	@Override
	public Class<?> getStateClass() {
		return State.class;
	}

	@Override
	public void playerJoin(int id) {
		// TODO Auto-generated method stub
		
	}

}
