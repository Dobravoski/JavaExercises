package ContractInstallmentsExercise;

public class PaypalService implements PaymentService {
    @Override
    public double paymentFee(double amount) {
        return amount * 0.02;
    }

    @Override
    public double interest(double amount, int month) {
        return amount * 0.01 * month;
    }
}