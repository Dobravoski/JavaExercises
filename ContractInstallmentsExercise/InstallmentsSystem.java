package ContractInstallmentsExercise;

import java.time.LocalDate;

public class InstallmentsSystem {
    private final PaymentService paymentService;

    public InstallmentsSystem(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public Installment[] generateInstallments(double amount, int installmentQuantity, LocalDate contractDate) {
        Installment[] installments = new Installment[installmentQuantity];
        double baseInstallment = amount/installmentQuantity;

        for (int i = 0; i < installmentQuantity; i++) {
            double interest = paymentService.interest(baseInstallment, i + 1) + baseInstallment;
            double fee = paymentService.paymentFee(interest) + interest;

            LocalDate dueDate = contractDate.plusMonths(i+1);

            installments[i] = new Installment(dueDate, fee);
        }

        return installments;
    }
}