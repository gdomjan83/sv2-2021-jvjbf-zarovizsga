package shipping;

public class InternationalPackage implements Transportable {
    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;
    public static final int SHIPPING_PRICE_DEFAULT = 1200;
    public static final int COST_PER_KM = 10;


    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
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
        int extraCost = kmCost(distance);
        return breakable ? ((SHIPPING_PRICE_DEFAULT * 2) + extraCost) : (SHIPPING_PRICE_DEFAULT + extraCost);
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    private int kmCost(int distance) {
        return distance * COST_PER_KM;
    }
}
