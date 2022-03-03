import factory.ComputerFactory;
import model.Computer;

public class Program {
    public static void main(String[] args){
        ComputerFactory factory = new ComputerFactory();
        Computer mac1 = factory.getComputer(16, 500);
        Computer windows = factory.getComputer(4, 256);
        Computer mac2 = factory.getComputer(32, 500);
        Computer mac3 = factory.getComputer(16, 500);


        System.out.println(mac1.toString());
        System.out.println(windows.toString());
        System.out.println(mac2.toString());
    }
}
