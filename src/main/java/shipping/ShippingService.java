package src.main.java.shipping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        // Group by name and weight
        Map<String, Integer> counts = new LinkedHashMap<>();
        Map<String, Double> weights = new LinkedHashMap<>();

        for (Shippable item : items) {
            String key = item.getName() + "|" + item.getWeight();
            counts.put(key, counts.getOrDefault(key, 0) + 1);
            weights.put(key, item.getWeight());  // store original weight once
        }

        double totalWeight = 0;

        // First line: 2x Cheese, 1x Biscuits
        for (String key : counts.keySet()) {
            String[] parts = key.split("\\|");
            String name = parts[0];
            System.out.printf("%dx %-12s%n", counts.get(key), name);
        }

        // Second line: weights in grams
        for (String key : counts.keySet()) {
            double weight = weights.get(key);
            int count = counts.get(key);
            for (int i = 0; i < count; i++) {
                System.out.printf("%.0fg%n", weight * 1000);
                totalWeight += weight;
            }
        }

        System.out.printf("Total package weight %.1fkg%n%n", totalWeight);
    }
}
