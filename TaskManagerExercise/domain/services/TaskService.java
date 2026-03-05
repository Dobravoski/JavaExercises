package TaskManagerExercise.domain.services;

import TaskManagerExercise.domain.entities.Task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskService {
    private final Path path = Path.of("src", "TaskManagerExercise", "application", "Tasks.txt");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");


    public void addTask(Task task) {
        try (var bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            String strTask = "%s;%s;%s;%s;%s".formatted(task.getTitle(), task.getType(), task.getDueDate().format(formatter),
                    task.getPriority(), task.getStatus());
            bw.write(strTask);
            bw.newLine();
            System.out.println("\nTask added successfully");
        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }

    public List<Task> listTasks() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(path)) {
            return tasks;
        }
        try(var br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                String[] data = line.split(";");
                tasks.add(new Task(data[0], Task.Type.valueOf(data[1]), LocalDate.parse(data[2], formatter), Task.Priority.valueOf(data[3]), Task.Status.valueOf(data[4])));
            }
        } catch (IOException e){
            System.out.println("Error while reading file: " + e.getMessage());
        }
        return tasks;
    }

    public void completeTask(int index, List<Task> tasks) {
        tasks.get(index).markAsDone();
        try (var bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Task task : tasks) {
                String strTask = "%s;%s;%s;%s;%s".formatted(task.getTitle(), task.getType(), task.getDueDate().format(formatter),
                        task.getPriority(), task.getStatus());
                bw.write(strTask);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while overwriting file: " + e.getMessage());
        }
        System.out.println("\nTask " + tasks.get(index).getTitle() + " marked as done successfully");
    }

    public List<Task> highPriorityTasks() {
        List<Task> highPriorityTasks = new ArrayList<>();
        for (Task task : listTasks()) {
            if(task.getPriority() == Task.Priority.VERY_HIGH || task.getPriority() == Task.Priority.HIGH) {
                highPriorityTasks.add(task);
            }
        }
        Collections.sort(highPriorityTasks);
        return highPriorityTasks;
    }

    public String taskToString(Task task, int count) {
        return """
                    [%d] %s
                    Type: %s
                    Due date: %s
                    Priority: %s
                    Status: %s""".formatted(count, task.getTitle(), task.getType(),task.getDueDate().format(formatter), task.getPriority(), task.getStatus());
    }
}
