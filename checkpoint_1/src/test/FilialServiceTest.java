package test;

import dao.ConfigJDBC;
import dao.impl.FilialDaoH2;
import model.Filial;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.FilialService;

import java.util.UUID;

public class FilialServiceTest {
    private static final Logger LOGGER = Logger.getLogger(FilialServiceTest.class);

    private FilialService filialService = new FilialService(
            new FilialDaoH2(
                    new ConfigJDBC()));


    @BeforeAll
    static void ConfigLog() {
        BasicConfigurator.configure();
    }

    @Test
    public void cadastrarFilial() {
        LOGGER.info("Cadastrando a filial no H2...");


        // teste 1
        Filial filial1 = new Filial(
                "Filial 1", "Rua dos Testes", true
        );
        Filial filial1Salvo = filialService.salvar(filial1);


        // teste 2
        Filial filial2 = new Filial(
                "Filial 2", "Rua do XML", true
        );
        Filial filial2Salvo = filialService.salvar(filial2);

        // teste 3
        Filial filial3 = new Filial(
                "Filial 3", "Rua do Tempo de Compilação", true
        );
        Filial filial3Salvo = filialService.salvar(filial3);

        // teste 4
        Filial filial4 = new Filial(
                "Filial 4", "Rua da Verbosidade", true
        );
        Filial filial4Salvo = filialService.salvar(filial4);

        // teste 5
        Filial filial5 = new Filial(
                "Filial 5", "Rua do Javascript", true
        );
        Filial filial5Salvo = filialService.salvar(filial5);
    }


}
