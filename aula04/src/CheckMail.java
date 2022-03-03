public class CheckMail {
    Gerenciador inicial;

    public CheckMail(){
        this.inicial = new GerenciadorGerencia();
        Gerenciador tecnica = new GerenciadorTecnica();
        Gerenciador comercial = new GerenciadorComercial();
        Gerenciador spam = new GerenciadorSpam();

        inicial.getSeguinte();
        comercial.getSeguinte();
        tecnica.getSeguinte();
    }

    public void verificar(Mail mail){
        inicial.verificar(mail);
    }

}
