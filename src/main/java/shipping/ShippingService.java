package shipping;

import java.util.*;

public class ShippingService {
    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable packageToAdd) {
        if (packageToAdd == null) {
            throw new IllegalArgumentException("Package is null.");
        }
        packages.add(packageToAdd);
    }

    public List<Transportable> getPackages() {
        return new ArrayList<>(packages);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
                .filter(t -> (t.isBreakable() == breakable) && t.getWeight() >= weight)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new HashMap<>();
        for (Transportable actual : packages) {
            String destination = actual.getDestinationCountry();
            countAndAddPackages(destination, result);
        }
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        List<Transportable> result = getPackages();
        return result.stream()
                .filter(t -> t instanceof InternationalPackage)
                .sorted(Comparator.comparing(t -> ((InternationalPackage) t).getDistance()))
                .toList();
    }

    private void countAndAddPackages(String destination, Map<String, Integer> result) {
        int numberOfPackages = (int) packages.stream()
                .filter(t -> destination.equals(t.getDestinationCountry()))
                .count();
        result.put(destination, numberOfPackages);
    }

}
