package model;

public class Arvore {
    private int largura;
    private int altura;
    private String cor;
    private String tipo;

    public Arvore(int largura, int altura, String cor, String tipo) {
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
        this.tipo = tipo;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Arvore{" +
                "largura=" + largura +
                ", altura=" + altura +
                ", cor='" + cor + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
