package TaskManagerExercise.domain.services;

import TaskManagerExercise.domain.entities.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private final FileRepository fileRepository = new FileRepository();

    public void updateFile(List<Task> tasks) {
        List<String> formattedTasks = new ArrayList<>();
        for (Task task : tasks) {
            String strTask = "%s;%s;%s;%s;%s".formatted(task.getTitle(), task.getType(), task.getDueDate().format(formatter),
                    task.getPriority(), task.getStatus());
            formattedTasks.add(strTask);
        }
        fileRepository.writeFile(formattedTasks);
    }

    public List<Task> listTasks() {
        List<Task> tasks = new ArrayList<>();
        List<String> strTasks = fileRepository.listFileData();
        for (String strTask : strTasks) {
            String[] data = strTask.split(";");
            tasks.add(new Task(data[0], Task.Type.valueOf(data[1]), LocalDate.parse(data[2], formatter), Task.Priority.valueOf(data[3]), Task.Status.valueOf(data[4])));
        }
        return tasks;
    }

    public void completeTask(Task task) {
        task.markAsDone();
        System.out.println("\nTask " + task.getTitle() + " complete");
    }

    public List<Task> highPriorityTasks(List<Task> tasks) {
        List<Task> highPriorityTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getPriority() == Task.Priority.VERY_HIGH || task.getPriority() == Task.Priority.HIGH) {
                highPriorityTasks.add(task);
            }
        }
        Collections.sort(highPriorityTasks);
        return highPriorityTasks;
    }

    public void sortByDate(List<Task> tasks) {
        Collections.sort(tasks,
                Comparator.comparing(Task::getDueDate).reversed());
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
