package EmployeesExercise;

public class Employee {
    private String name;
    private int hours;
    private double valuePerHour;

    public Employee(String name, int hours, double valuePerHour) {
        this.name = name;
        this.hours = hours;
        this.valuePerHour = valuePerHour;
    }

    protected double payment() {
        return hours * valuePerHour;
    }

    @Override
    public String toString() {
        return name + " - $" + payment();
    }
}
