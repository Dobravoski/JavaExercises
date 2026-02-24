package ProductsExercise;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainIO {
    static void main() {
        List<PurchaseSummary> purchaseSummary = readFile(".\\src\\ProductsExercise\\products.csv");
        writeFile(".\\src\\ProductsExercise\\summary.csv", purchaseSummary);

        // Using to read: File, BufferedReader + FileReader
        // Using to write: File, BufferedWriter + FileWriter
    }

    public static List<PurchaseSummary> readFile(String filePath) {
        File file = new File(filePath);
        List<PurchaseSummary> purchaseSummaries = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                double totalPrice = Double.parseDouble(data[1]) * Double.parseDouble(data[2]);
                purchaseSummaries.add(new PurchaseSummary(name, totalPrice));
            }
            return purchaseSummaries;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file");
        }
    }

    public static void writeFile(String filePath, List<PurchaseSummary> purchaseSummary) {
        File file = new File(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) { // FileWriter(file) create the file
            for (int i = 0; i < purchaseSummary.size(); i++) {
                bw.write(purchaseSummary.get(i).toString());
                if (i != purchaseSummary.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing file");
        }
    }
}