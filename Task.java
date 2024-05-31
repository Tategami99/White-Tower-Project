import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title;
    private String description;
    private LocalDateTime dueDateTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String title, String description, LocalDateTime dueDateTime) {
        this.title = title;
        this.description = description;
        this.dueDateTime = dueDateTime;
    }

    /**
    * @return title
    */
    public String getTitle() {
        return title;
    }
    
    /**
    * @return description
    */
    public String getDescription() {
        return description;
    }

    /**
    * @return when the task is due
    */
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    @Override
    public String toString() {
        return title + "\n" + description + "\n" + dueDateTime.format(formatter) + "\n";
    }

    /**
    * @return a Task object from a String
    */
    public static Task fromString(String taskString) {
        String[] parts = taskString.split("\n");
        if (parts.length != 3) { // if more than title, description, and when its due
            throw new IllegalArgumentException("Invalid task format");
        }
        String title = parts[0];
        String description = parts[1];
        LocalDateTime dueDateTime = LocalDateTime.parse(parts[2], formatter);
        return new Task(title, description, dueDateTime);
    }
}
