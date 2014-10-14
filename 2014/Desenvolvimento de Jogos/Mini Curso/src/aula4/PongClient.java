package aula4;

import aula4.client.PongApplication;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class PongClient extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;
	
	public PongClient() {
		super(800, 600);
	}

	public static void main(String[] args) throws Exception {
		PongClient game = new PongClient();
		game.init();
	}

	@Override
	public Application startApplication() {
		
		String path = PongClient.class.getResource("").toString();
		setPath(path+"../../");
		
		return new PongApplication(w, h);
	}

}
