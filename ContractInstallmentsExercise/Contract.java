package ContractInstallmentsExercise;

import java.time.LocalDate;

public class Contract {
    private final int contractNumber;
    private final LocalDate contractDate;
    private final double contractAmount;
    private final int installmentQuantity;

    public Contract(int contractNumber, LocalDate contractDate, double contractAmount, int installmentQuantity) {
        this.contractNumber = contractNumber;
        this.contractDate = contractDate;
        this.contractAmount = contractAmount;
        this.installmentQuantity = installmentQuantity;
    }

    public double getContractAmount() {
        return contractAmount;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public int getInstallmentQuantity() {
        return installmentQuantity;
    }

}