public class Estagiario extends Vendedor{

    public Estagiario(String nome){
        super.nome = nome;
        super.PONTOS_POR_VENDA = 5;
    }

    @Override
    public int calcularPontos() {
        return this.vendas * PONTOS_POR_VENDA;
    }

    @Override
    public String mostrarCategoria() {
        int pontos = this.calcularPontos();
        String name = this.nome;


        if(pontos < 50){
            return name + " é estágiário novato, " + "com " + pontos + " pontos.";
        }

        if(pontos >= 50){
            return name + " é estágiário experiente, " + "com " + pontos + " pontos.";
        }

        return "Pontos negativos? WTF?";
    }
}
