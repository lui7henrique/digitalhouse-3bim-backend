public class Main {
    public static void Main(String[] args){
        Menu adultMenu = new AdultMenu(50);
        Menu childrensMenu = new ChildrensMenu(50);
        Menu vegetarianMenu = new VegetarianMenu(50);


        adultMenu.toString();
    }
}
