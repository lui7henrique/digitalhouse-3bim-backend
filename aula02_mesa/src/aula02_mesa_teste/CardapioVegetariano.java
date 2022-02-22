package aula02_mesa_teste;

public class CardapioVegetariano extends CardapioAdulto {
    private double custoEmbalagem;

    public CardapioVegetariano(double precoBase, double custoEmbalagem) {
        super(precoBase);
        this.custoEmbalagem = custoEmbalagem;
    }

    public double getCustoEmbalagem() {
        return custoEmbalagem;
    }

    public void setCustoEmbalagem(double custoEmbalagem) {
        this.custoEmbalagem = custoEmbalagem;
    }

    @Override
    public void montarCardapio() {
        System.out.println("Montando o cardápio vegetariano, com condimentos e embalagem especial");
    }

    @Override
    public void calcularPreco() {
        double valor = (getPrecoBase() * 1.01) + custoEmbalagem;
        System.out.println("O preço do cardápio é de " + valor);
    }
}
