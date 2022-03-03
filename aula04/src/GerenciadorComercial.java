public class GerenciadorComercial extends Gerenciador {

    @Override
    public void verificar(Mail email){
        if(email.getDestino().equalsIgnoreCase("comercial@colmeia.com") || email.getAssunto().equalsIgnoreCase("Comercial")){
            System.out.println("Enviado ao departamento comercial");
        }

        if (this.getSeguinte() != null){
            this.getSeguinte().verificar(email);
        }
    }
}
