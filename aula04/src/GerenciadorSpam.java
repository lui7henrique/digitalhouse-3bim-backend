public class GerenciadorSpam extends Gerenciador {

    @Override
    public void verificar(Mail email){
        if(email.getDestino().equalsIgnoreCase("spam@colmeia.com") || email.getAssunto().equalsIgnoreCase("Spam")){
            System.out.println("");
        }

        if (this.getSeguinte() != null){
            this.getSeguinte().verificar(email);
        }
    }
}
