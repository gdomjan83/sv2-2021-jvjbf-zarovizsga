package shipping;

public class NationalPackage implements Transportable {
    private int weight;
    private boolean breakable;
    public static final int SHIPPING_PRICE_DEFAULT = 1000;

    public NationalPackage(int weight, boolean breakable) {
        this.weight = weight;
        this.breakable = breakable;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public int calculateShippingPrice() {
        return breakable ? (SHIPPING_PRICE_DEFAULT * 2) : (SHIPPING_PRICE_DEFAULT);
    }
}
