package ProductsExercise;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainNIO2 {
    static void main() {
        writeFile(readFile());

        // Using to read: Path, Files.lines, Stream
        // Using to write: Path, Files.newBufferedWriter (BufferedWriter)
    }

    public static List<PurchaseSummary> readFile() {
        Path path = Path.of("src", "ProductsExercise", "products.csv");
        List<PurchaseSummary> purchaseSummary = new ArrayList<>();

        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.forEach(line -> {
                String[] data = line.split(",");
                String name = data[0];
                double totalPrice = Double.parseDouble(data[1]) * Double.parseDouble(data[2]);
                purchaseSummary.add(new PurchaseSummary(name, totalPrice));
            });
        } catch (IOException e) {
            throw new RuntimeException("Error reading file");
        }
        return purchaseSummary;
    }

    public static void writeFile(List<PurchaseSummary> purchaseSummary) {
        Path path = Path.of("src", "ProductsExercise", "summary.csv");

        try(var bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
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
