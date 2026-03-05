package TaskManagerExercise.domain.entities;

import java.time.LocalDate;

public class Task implements Comparable<Task> {
    private final String title;
    private final Type type;
    private final LocalDate dueDate;
    private final Priority priority;
    private Status status;

    public Task(String title, Type type, LocalDate dueDate, Priority priority, Status status) {
        this.title = title;
        this.type = type;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    public enum Status {
        PENDING,
        DONE;
    }
    public enum Type {
        WORK,
        PERSONAL;
    }
    public enum Priority {
        VERY_LOW,
        LOW,
        MEDIUM,
        HIGH,
        VERY_HIGH;
    }

    public void markAsDone() {
        this.status = Status.DONE;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public int compareTo(Task other) {
        return other.priority.compareTo(this.priority);
    }
}
