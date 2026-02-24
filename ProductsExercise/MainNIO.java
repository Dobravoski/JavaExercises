package ProductsExercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MainNIO {
    static void main() {
        writeFile(readFile());

        // Using to read: Path, Files.newBufferedReader (BufferedReader)
        // Using to write: Path, Files.newBufferedWriter (BufferedWriter)
    }

    public static List<PurchaseSummary> readFile() {
        List<PurchaseSummary> purchaseSummary = new ArrayList<>();

        Path path = Path.of("src", "ProductsExercise", "products.csv"); // doesn't depend on the OS

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) { // var br = br = Files.newBufferedReader(path))
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                double totalPrice = Double.parseDouble(data[1]) * Double.parseDouble(data[2]);
                purchaseSummary.add(new PurchaseSummary(name, totalPrice));
            }
            return purchaseSummary;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file");
        }
    }

    public static void writeFile(List<PurchaseSummary> purchaseSummary) {
        Path path = Path.of("src", "ProductsExercise", "summary.csv");

        try(var bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) { // Files.newBufferedWriter(path) creates the file
            for (int i = 0; i < purchaseSummary.size(); i++) {                // StandardCharsets.UTF_8 international standard text
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
