package aula02_mesa_teste;

public class CardapioAdulto extends Cardapio {
    public CardapioAdulto(double precoBase) {
        super(precoBase);
    }

    @Override
    public void montarCardapio(){
        System.out.println("Montando o cardápio adulto, sem adicionais");
    }

    @Override
    public void calcularPreco(){
        System.out.println("O preço do cardápio é de " + getPrecoBase());
    }
}