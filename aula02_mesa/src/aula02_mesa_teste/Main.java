package aula02_mesa_teste;

public class Main {
    public static void main(String[] args) {
        Cardapio cardAdulto = new CardapioAdulto(150.0);
        Cardapio cardInfantil = new CardapioInfantil(120.0, 10.0);
        Cardapio cardVeggie = new CardapioVegetariano(100.0, 10.0);

        cardAdulto.prepararCardapio();
        cardInfantil.prepararCardapio();
        cardVeggie.prepararCardapio();
    }
}


