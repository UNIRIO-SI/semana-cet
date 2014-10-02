import aula1.Exemplo1;
import aula1.Exemplo2;
import aula1.Exemplo3;
import aula1.Exemplo4;
import aula1.Exemplo5;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;


public class Project extends EtyllicaFrame {

	private static final long serialVersionUID = 1L;

	public Project() {
		super(960, 480);
	}
	
	public static void main(String[] args) {
		Project project = new Project();
		project.init();
	}

	@Override
	public Application startApplication() {
		
		String path = Project.class.getResource("").toString();
		setPath(path+"../");
		
		//return new Exemplo1(w, h);
		//return new Exemplo2(w, h);
		//return new Exemplo3(w, h);
		return new Exemplo4(w, h);
		//return new Exemplo5(w, h);
	}

}
