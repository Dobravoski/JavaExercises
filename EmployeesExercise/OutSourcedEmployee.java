package EmployeesExercise;

public class OutSourcedEmployee extends Employee {

    private final double additionalCharge;

    public OutSourcedEmployee(String name, int hours, double valuePerHour, double additionalCharge) {
        super(name, hours, valuePerHour);
        this.additionalCharge = additionalCharge;
    }

    @Override
    protected double payment() {
        return super.payment() +  additionalCharge*1.1;
    }
}
