package ProductsExercise;

public class PurchaseSummary {
    private String productName;
    private double totalPrice;

    public PurchaseSummary(String productName, double totalPrice) {
        this.productName = productName;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "%s, %.2f".formatted(productName, totalPrice);
    }
}
