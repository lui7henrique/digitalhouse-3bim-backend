import dao.impl.DentistaDaoH2;
import dao.impl.EnderecoDaoH2;
import model.Dentista;
import model.Endereco;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import service.DentistaService;
import service.EnderecoService;

import java.util.List;

class Aula21ApplicationTests {
	private static DentistaService dentistaService = new DentistaService(
			new DentistaDaoH2()
	);

	private static EnderecoService enderecoService = new EnderecoService(
			new EnderecoDaoH2()
	);

	@Test
	void contextLoads() {
	}

	@Test
	public void carregarTresDentista(){
		Dentista d1 = new Dentista("Luiz", "pramos@gmail.com", 21547, 0);
		Dentista d2 = new Dentista("Henrique", "pramos@gmail.com", 21547, 0);
		Dentista d3 = new Dentista("Delfino", "pramos@gmail.com", 21547, 0);

		dentistaService.salvar(d1);
		dentistaService.salvar(d2);
		dentistaService.salvar(d2);
	}

	@Test
	public void listarTodosDentista(){
		List<Dentista> dentistasList = dentistaService.buscarTodos();

		System.out.println(dentistasList);
	}


	@Test
	public void criarTresEnderecos(){
		Endereco e1 = new Endereco("Rua das Mentiras", 230, "Suzano", "Boa Vista", "SP");
		Endereco e2 = new Endereco("Rua Fantasiosa", 500, "Suzano", "Badra", "SP");
		Endereco e3 = new Endereco("Rua Sla mano", 300, "Suzano", "Jardim Paulista", "SP");


		enderecoService.salvar(e1);
		enderecoService.salvar(e2);
		enderecoService.salvar(e3);
	}

	@Test
	public void listarEnderecos(){
		List<Endereco> enderecosList = enderecoService.buscarTodos();

		System.out.println(enderecosList);
	}

}
