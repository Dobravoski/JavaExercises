package TaskManagerExercise.application;

import TaskManagerExercise.domain.entities.Task;
import TaskManagerExercise.domain.services.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Program {
    static void main() {
        menu();
    }

    public static void menu() {
        var sc = new Scanner(System.in);
        var taskService = new TaskService();
        List<Task> tasks = taskService.listTasks();

        outer: while (true) {
            System.out.println("\n===== TASK MANAGER =====\n");
            System.out.print("""
                    1 - List tasks
                    2 - Add task
                    3 - Mark task as done
                    4 - List high priority tasks
                    5 - Exit
                    """);
            switch (intScanner(1, 5, sc)) {
                case 1:
                    listTasks(taskService, tasks);
                    break;
                case 2:
                    System.out.println("\n===== NEW TASK =====\n");
                    System.out.println("Title: ");
                    System.out.print(">> ");
                    String title = sc.nextLine();
                    System.out.println("Type (1 - WORK | 2 - PERSONAL)");
                    Task.Type type = switch (intScanner(1, 2, sc)) {
                        case 1 -> Task.Type.WORK;
                        case 2 -> Task.Type.PERSONAL;
                        default -> null;
                    };
                    System.out.println("Due Date");
                    LocalDate dueDate = dateScanner(sc);
                    System.out.println("""
                            Priority
                            1 - Very low
                            2 - Low
                            3 - Medium
                            4 - High
                            5 - Very high""");
                    Task.Priority priority = switch (intScanner(1, 5, sc)) {
                        case 1 -> Task.Priority.VERY_LOW;
                        case 2 -> Task.Priority.LOW;
                        case 3 -> Task.Priority.MEDIUM;
                        case 4 -> Task.Priority.HIGH;
                        case 5 -> Task.Priority.VERY_HIGH;
                        default -> null;
                    };
                    tasks.add(new Task(title, type, dueDate, priority, Task.Status.PENDING));
                    taskService.sortByDate(tasks);
                    break;
                case 3:
                    System.out.println("Task index");
                    taskService.completeTask(tasks.get(intScanner(0, tasks.size()-1, sc)));
                    break;
                case 4:
                    listTasks(taskService, taskService.highPriorityTasks(tasks));
                    break;
                case 5:
                    System.out.println("Saving the file...");
                    taskService.updateFile(tasks);
                    System.out.println("Exiting");
                    break outer;
            }
        }

        sc.close();
    }

    public static int intScanner(int start, int end, Scanner sc) {
        while (true) {
            try {
                System.out.print(">> ");
                int result = Integer.parseInt(sc.nextLine());
                if (result < start || result > end) {
                    System.out.println("The input must be between " + start + " and " + end);
                    continue;
                }
                return result;
            }  catch (NumberFormatException e) {
                System.out.println("Invalid input, try again");
            }
        }
    }

    public static LocalDate dateScanner(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            try {
                System.out.print(">> ");
                return LocalDate.parse(sc.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("The input must be dd-MM-yyyy, try again");
            }
        }
    }

    public static void listTasks(TaskService taskService, List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks have been added.");
            return;
        }
        System.out.println("\n===== TASKS LIST =====\n");
        for (int count = 0; count < tasks.size(); count++) {
            System.out.println(taskService.taskToString(tasks.get(count), count));
            System.out.println("\n-----------------------------------\n");
        }
    }
}
