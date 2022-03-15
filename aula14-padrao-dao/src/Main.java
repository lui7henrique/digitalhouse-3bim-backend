import dao.ConfigJDBC;
import dao.impl.MedicamentoDaoH2;
import model.Medicamento;
import service.MedicamentoService;

public class Main {
    public static void main(String[] args){
        MedicamentoService medicamentoService = new MedicamentoService(
                new MedicamentoDaoH2(
                        new ConfigJDBC()
                )
        );

        Medicamento medicamento = new Medicamento(
                "Ibuprofeno","Aché", 395, 300.00
        );


        medicamentoService.salvar(medicamento);
    }
}
