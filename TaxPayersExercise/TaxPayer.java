package TaxPayersExercise;

public abstract class TaxPayer {
    private String name;
    private double anualIncome;

    public TaxPayer(String name, double anualIncome) {
        this.name = name;
        this.anualIncome = anualIncome;
    }

    protected double getAnualIncome() {return anualIncome;}

    public abstract double tax();

    @Override
    public String toString() {
        return String.format("%s: $%.2f", name, tax());
    }
}
