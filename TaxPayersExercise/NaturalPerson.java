package TaxPayersExercise;

public class NaturalPerson extends TaxPayer {

    private double healthExpenditure;

    public NaturalPerson(String name, double anualIncome, double healthExpenditure) {
        super(name, anualIncome);
        this.healthExpenditure = healthExpenditure;
    }

    @Override
    public double tax() {
        if (getAnualIncome() < 20000) {
            return (getAnualIncome() * 0.15) - (healthExpenditure*0.5);
        } else {
            return (getAnualIncome() * 0.25) - (healthExpenditure*0.5);
        }
    }
}
