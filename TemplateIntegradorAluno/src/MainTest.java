import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    Funcionario func1, func2;
    Afiliado afi1, afi2, afi3;
    Estagiario estagiario1, estagiario2, estagiario3;

    @BeforeEach
    public void doBefore() {
        func1 = new Funcionario("Pedro", 2);
        func1.vender(2);

        func2 = new Funcionario("Maria",1);
        func2.vender(1);

        afi1 = new Afiliado("Ramon");
        afi1.vender(4);

        afi2 = new Afiliado("Paulo");
        afi2.vender(1);

        afi3 = new Afiliado("José");

        estagiario1 = new Estagiario("Harry");
        estagiario1.vender(1);

        estagiario2 = new Estagiario("Roni");
        estagiario2.vender(5);

        estagiario3 = new Estagiario("Hermione");
        estagiario3.vender(10);

        func1.addAfiliado(afi1);
    }

    @Test
    public void mostrarVendedores() {
        System.out.println(func1.mostrarCategoria());
        System.out.println(func2.mostrarCategoria());
        System.out.println(afi1.mostrarCategoria());
        System.out.println(afi2.mostrarCategoria());
        System.out.println(afi3.mostrarCategoria());

        System.out.println(estagiario1.mostrarCategoria());
        System.out.println(estagiario2.mostrarCategoria());
        System.out.println(estagiario3.mostrarCategoria());
    }

}