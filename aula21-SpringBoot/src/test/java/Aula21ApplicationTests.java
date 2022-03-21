import dao.impl.DentistaDaoH2;
import model.Dentista;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.DentistaService;

class Aula21ApplicationTests {
	private static DentistaService dentistaService = new DentistaService(
			new DentistaDaoH2()
	);

	@Test
	void contextLoads() {
	}

	@Test
	public void carregarTresDentista(){
		Dentista d1 = new Dentista("Luiz", "pramos@gmail.com", 21547, 0);
		Dentista d2 = new Dentista("Luiz", "pramos@gmail.com", 21547, 0);
		Dentista d3 = new Dentista("Luiz", "pramos@gmail.com", 21547, 0);

		dentistaService.salvar(d1);
	}

}
