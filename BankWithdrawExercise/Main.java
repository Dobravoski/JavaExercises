package BankWithdrawExercise;

import java.util.Scanner;

public class Main {
    static void main() {
        var sc = new Scanner(System.in);
        Account account;

        while (true) {
            try {
                System.out.println("Enter account data");
                System.out.print("Account number: ");
                int accountNumber = Integer.parseInt(sc.nextLine());
                System.out.print("Account holder: ");
                String holder = sc.nextLine();
                System.out.print("Balance: ");
                double balance = Double.parseDouble(sc.nextLine());
                System.out.print("Withdrawal limit: ");
                double withdrawLimit = Double.parseDouble(sc.nextLine());

                account = new Account(accountNumber, holder, balance, withdrawLimit);

                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                System.out.println();
            }
        }

        while (true) {
            try {
                System.out.print("\nEnter amount to withdraw: ");
                account.withdraw(Double.parseDouble(sc.nextLine()));
                System.out.println(account);
                break;
            } catch (DomainException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }
        }
        sc.close();
    }
}
