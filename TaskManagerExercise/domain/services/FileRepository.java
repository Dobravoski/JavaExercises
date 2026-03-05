package TaskManagerExercise.domain.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private final Path path = Path.of("src", "TaskManagerExercise", "application", "Tasks.txt");

    public void writeFile(List<String> listToWrite) {
        try (var bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String str : listToWrite) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }

    public List<String> listFileData() {
        List<String> dataList = new ArrayList<>();
        if (!Files.exists(path)) {
            return dataList;
        }
        try(var br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (IOException e){
            System.out.println("Error while reading file: " + e.getMessage());
        }
        return dataList;
    }
}
