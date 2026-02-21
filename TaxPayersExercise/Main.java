package TaxPayersExercise;

import java.util.Scanner;

public class Main {
    static void main() {
        var sc = new Scanner(System.in);

        System.out.print("Enter the number of tax payers: ");
        TaxPayer[] taxPayers = new TaxPayer[Integer.parseInt(sc.nextLine())];

        for (int i = 0; i < taxPayers.length; i++) {
            System.out.println("Tax payer " + (i + 1) + ":");
            System.out.print("Individual or company (i/c)? ");
            char naturalPersonOrLegalEntity = sc.nextLine().charAt(0);
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Anual income: ");
            double anualIncome = Double.parseDouble(sc.nextLine());

            if (naturalPersonOrLegalEntity == 'i') {
                System.out.print("Health expenditures: ");
                double healthExpenditures = Double.parseDouble(sc.nextLine());
                taxPayers[i] = new NaturalPerson(name, anualIncome, healthExpenditures);
            } else {
                System.out.print("Number of employees: ");
                int numberOfEmployees = Integer.parseInt(sc.nextLine());
                taxPayers[i] = new LegalEntity(name, anualIncome, numberOfEmployees);
            }
        }

        double totalTax = 0;
        for (TaxPayer taxPayer : taxPayers) {
            System.out.println(taxPayer.toString());
            totalTax += taxPayer.tax();
        }

        System.out.printf("TOTAL TAXES: %.2f", totalTax);

        sc.close();
    }
}
