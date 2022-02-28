package shipping;

import java.util.ArrayList;
import java.util.List;

public class ShippingService {
    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable packageToAdd) {
        packages.add(packageToAdd);
    }

    public List<Transportable> getPackages() {
        return new ArrayList<>(packages);
    }
}
