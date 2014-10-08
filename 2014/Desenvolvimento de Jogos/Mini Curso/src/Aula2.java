import aula2.Exemplo6;
import aula2.Exemplo9;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class Aula2 extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public Aula2() {
		super(512, 384);
	}
	
	public static void main(String[] args) {
		Aula2 project = new Aula2();
		project.setUndecorated(true);
		project.init();
	}

	@Override
	public Application startApplication() {
		
		String path = Aula2.class.getResource("").toString();
		setPath(path+"../");
		
		return new Exemplo9(w, h);
	}

}
