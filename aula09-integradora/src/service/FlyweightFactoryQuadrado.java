package service;

import model.Quadrado;

import java.util.HashMap;

public class FlyweightFactoryQuadrado {
    public static final HashMap<Number, Quadrado> quadradoMap = new HashMap<>();

    public static Quadrado obterQuadrado(int tamanho){
        Quadrado quadrado = quadradoMap.get(tamanho);

        if(quadrado == null){
            quadrado = new Quadrado(tamanho);
            quadradoMap.put(tamanho, quadrado);
        }

        return quadrado;
    }
}
