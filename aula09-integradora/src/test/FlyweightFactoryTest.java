package test;

import model.Quadrado;
import model.Triangulo;
import org.junit.jupiter.api.Test;
import service.FlyweightFactoryQuadrado;
import service.FlyweightFactoryTriangulo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlyweightFactoryTest {
    @Test
    void getTrianguloTComTamanho2(){
        // triangulos;
        Triangulo triangulo1 = FlyweightFactoryTriangulo.obterTriangulo("vermelho");
        triangulo1.setTamanho(2);

        Triangulo triangulo2 = FlyweightFactoryTriangulo.obterTriangulo("azul");
        triangulo2.setTamanho(4);

        Triangulo triangulo3 = FlyweightFactoryTriangulo.obterTriangulo("azul");

        assertEquals(FlyweightFactoryTriangulo.trianguloMap.size(), 2);

        // quadrados
        Quadrado quadrado1 = FlyweightFactoryQuadrado.obterQuadrado(1);
        quadrado1.setCor("vermelho");

        Quadrado quadrado2 = FlyweightFactoryQuadrado.obterQuadrado(2);
        quadrado2.setCor("vermelho");

        Quadrado quadrado3 = FlyweightFactoryQuadrado.obterQuadrado(2);

        assertEquals(FlyweightFactoryQuadrado.quadradoMap.size(), 2); // 2
    }
}
