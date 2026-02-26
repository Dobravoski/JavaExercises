package ContractInstallmentsExercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    static void main() {
        var sc = new Scanner(System.in);
        var installmentsSystem = new InstallmentsSystem(new PaypalService());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.println("Please enter the contract details:");
        System.out.print("Contract Number: ");
        int contractNumber = Integer.parseInt(sc.nextLine());
        System.out.print("Contract date: ");
        LocalDate contractDate = LocalDate.parse(sc.nextLine(), formatter);
        System.out.print("Contract amount: ");
        double contractAmount = Double.parseDouble(sc.nextLine());
        System.out.print("Number of installments: ");
        int numberOfInstallments = Integer.parseInt(sc.nextLine());

        Contract contract = new Contract(contractNumber, contractDate, contractAmount, numberOfInstallments);

        System.out.print("\nContract number: " + contract.getContractNumber());
        for (Installment instalment : installmentsSystem.generateInstallments(contract.getContractAmount(), contract.getInstallmentQuantity(), contract.getContractDate())) {
            System.out.printf("\n%s - %.2f", instalment.getDueDate().format(formatter), instalment.getInstallmentAmount());
        }

        sc.close();
    }
}