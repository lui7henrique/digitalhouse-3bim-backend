package aula02_mesa_teste;

public abstract class Cardapio {
    private double precoBase;

    public Cardapio(double precoBase) {
        this.precoBase = precoBase;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public void prepararCardapio(){
        montarCardapio();
        calcularPreco();
    }

    public abstract void montarCardapio();

    public abstract void calcularPreco();
}