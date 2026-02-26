package ContractInstallmentsExercise;

public interface PaymentService {
    double paymentFee(double amount);
    double interest(double amount, int month);
}