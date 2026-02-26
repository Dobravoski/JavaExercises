package ContractInstallmentsExercise;

import java.time.LocalDate;

public class Installment {
    private LocalDate dueDate;
    private double installmentAmount;

    public Installment(LocalDate dueDate, double installmentAmount) {
        this.dueDate = dueDate;
        this.installmentAmount = installmentAmount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }
}