import factory.FlorestaFactory;
import model.Arvore;

public class Program {
    public static void main(String[] args){
        FlorestaFactory factory = new FlorestaFactory();

        Arvore arvore1 = factory.getArvore(200, 400,"verde", "ornamental");
        Arvore arvore2 = factory.getArvore(500, 300,"vermelho", "frutífera");
        Arvore arvore3 = factory.getArvore(100, 200,"azul", "florífera");
        Arvore arvore4 = factory.getArvore(100, 200,"azul", "florífera");

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Memória utilizada: " + (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024));
    }
}
