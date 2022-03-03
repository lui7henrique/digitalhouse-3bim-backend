package factory;

import model.Arvore;

import java.util.HashMap;
import java.util.Map;

public class FlorestaFactory {
    private static Map<String, Arvore> macFlyweight = new HashMap<>();

    public Arvore getArvore(int altura, int largura, String cor, String tipo){
        String arvore = "Árvore: " + altura + "/" + largura + "/" + cor + "/" + tipo;
        System.out.println(arvore);

        if(!macFlyweight.containsKey(arvore)){
            macFlyweight.put(arvore, new Arvore(altura, largura, cor, tipo));
            System.out.println("Árvore criada");
            return macFlyweight.get(arvore);
        }

        System.out.println("Árvore já registrada na floresta");
        return macFlyweight.get(arvore);
    }
}
