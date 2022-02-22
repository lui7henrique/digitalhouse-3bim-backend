public abstract class Menu {
    private  float basePrince;

    public Menu(float basePrince) {
        this.basePrince = basePrince;
    }

    public float getBasePrince() {
        return basePrince;
    }

    public void setBasePrince(float basePrince) {
        this.basePrince = basePrince;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "basePrince=" + basePrince +
                '}';
    }
}