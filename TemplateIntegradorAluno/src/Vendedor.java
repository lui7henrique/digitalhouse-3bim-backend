
//validação que usa propriedades de subclasse apenas!!
//Classe abstrata Vendedor - aqui vai o Template Method
public abstract class Vendedor {

    protected String nome;
    protected int vendas = 0;
    protected int PONTOS_POR_VENDA;
    protected String categoria;


    public void vender(int qtdVendas){
        this.vendas += qtdVendas;
        System.out.println(this.nome +" realizou "+ qtdVendas + " vendas.");
    }

    /*Método que calcula os pontos do Vendedor de acordo com seus aspectos
        a serem considerados*/
    public abstract int calcularPontos();

    /*TEMPLATE METHOD - recebe o total de pontos calculados a partir
    da subclasse e valida cada item para retornar a categoria*/
    public String mostrarCategoria(){
        int pontos = this.calcularPontos();
        String name = this.nome;


        if(pontos < 20){
           return name + " é novato, " + "com " + pontos + " pontos.";
        }

        if(pontos >= 20 && pontos <= 30){
            this.categoria = "aprendiz";
            return name + " é aprendiz, " + " com " + pontos + " pontos.";

        }

        if(pontos >= 31 && pontos <= 40){
            this.categoria = "bom";
            return name + " é bom, " + "com " + pontos + " pontos.";
        }

        if(pontos > 40){
            this.categoria = "mestre";
            return name + " é mestre," + "com " + pontos + " pontos.";
        }

        return "Pontos negativos? WTF?";
    }
}





