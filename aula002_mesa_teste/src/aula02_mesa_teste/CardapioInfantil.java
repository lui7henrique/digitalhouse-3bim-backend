package aula02_mesa_teste;

public class CardapioInfantil extends CardapioAdulto {
    private double custoPresente;

    public CardapioInfantil(double precoBase, double custoPresente) {
        super(precoBase);
        this.custoPresente = custoPresente;
    }

    public double getCustoPresente() {
        return custoPresente;
    }

    public void setCustoPresente(double custoPresente) {
        this.custoPresente = custoPresente;
    }

    @Override
    public void montarCardapio() {
        System.out.println("Montando o cardápio infantil, com presente surpresa");
    }

    @Override
    public void calcularPreco() {
        double valor = getPrecoBase() + custoPresente;
        System.out.println("O preço do cardápio é de " + valor);
    }
}