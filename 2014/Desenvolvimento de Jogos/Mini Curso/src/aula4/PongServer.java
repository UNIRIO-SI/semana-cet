package aula4;
import examples.action.adapter.kryo.KryoActionServer;
import examples.action.model.State;
import aula4.server.PongServerListener;
import br.com.etyllica.network.server.Server;


public class PongServer {

	public static final int ACTION_TCP_PORT = 4455;
	public static final int ACTION_UDP_PORT = 4466;
	
	public static void main(String[] args) throws Exception {
		
		//Window Size
		final int w = 800;
		final int h = 600;
		
		//Interval between updates
		final int interval = 80;
		PongServerListener serverListener = new PongServerListener(interval, w, h);
		
		Server server = new KryoActionServer<State>(ACTION_TCP_PORT, ACTION_UDP_PORT, serverListener);
		
		server.init();
		server.prepare();
		server.bind();
		
		new Thread(serverListener).start();
	}
	
}
