import aula3.Exemplo10;
import aula3.Exemplo11;
import aula3.Exemplo12;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class Aula3 extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public Aula3() {
		super(768, 417);
	}
	
	public static void main(String[] args) {
		Aula3 project = new Aula3();
		project.init();
	}

	@Override
	public Application startApplication() {
		
		String path = Aula3.class.getResource("").toString();
		setPath(path+"../");
		
		return new Exemplo11(w, h);
	}

}
