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


        Filial filial = new Filial(
                "Teste", "Rua Hilda Smith do Amaral", true
        );

        Filial filialSalvo = filialService.salvar(filial);
    }


}
