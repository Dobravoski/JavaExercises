package EmployeesExercise;

import java.util.Scanner;

public class Main {
    static void main() {
        var sc = new Scanner(System.in);

        System.out.print("Enter the number of employees: ");
        Employee[] employees = new Employee[Integer.parseInt(sc.nextLine())];

        for (int i = 1; i < employees.length+1; i++) {
            System.out.println("Employee " + i + " data:");
            System.out.print("Outsourced (y/n)? ");
            char outsourced = sc.nextLine().charAt(0);
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Hours: ");
            int hours = Integer.parseInt(sc.nextLine());
            System.out.print("Value per hour: ");
            double valuePerHour = Double.parseDouble(sc.nextLine());

            if (outsourced == 'y' || outsourced == 'Y') {
                System.out.print("Additional charge: ");
                double additionalCharge = Double.parseDouble(sc.nextLine());
                employees[i-1] = new OutSourcedEmployee(name, hours, valuePerHour, additionalCharge);
            } else {
                employees[i-1] = new Employee(name, hours, valuePerHour);
            }
        }

        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }

        sc.close();
    }
}
