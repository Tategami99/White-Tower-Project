import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/*+----------------------------------------------------------------------
 ||
 ||  Class Task
 ||
 ||         Author:  Kaden Bautista & Neil Marquez
 ||
 ||        Purpose:  an easy way to group and represent the data for a task, as well as compare tasks to each other
 ||                  and
 ||
 ||  Inherits From:  none
 ||
 ||     Interfaces:  Comparable
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  formatter - DateTimeFormatter for formatting the user entered date and time
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  default - 
 ||                      title - title of the task
 ||                      description - description of the task
 ||                      dueDateTime - the date and time the task is due
 ||
 ||  Class Methods:  fromString, String argument, returns a Task object
 ||
 ||  Inst. Methods:  getTitle, no arguments, returns the title(String)
 ||                  getDescription, no arguments, returns the description(String)
 ||                  getDueDateTime, no arguments, returns the dueDateTime(LocalDateTime)
 ||                  toString, no arguments, returns the task's title, description, and dueDateTime as a formatted String
 ||                  compareTo, Task argument, returns an int representing which task is due first
 ||                  shouldRemiind, no arguments, returns a boolean representing whether or not the to remind the user of
 ||                      of this task based on the current time
 ||
 ||
 ++-----------------------------------------------------------------------*/
public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private LocalDateTime dueDateTime;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String title, String description, LocalDateTime dueDateTime) {
        this.title = title;
        this.description = description;
        this.dueDateTime = dueDateTime;
    }

    /*---------------------------------------------------------------------
     |  Method getTitle, getDescription, getDueDateTime
     |
     |  Purpose:  return the value of the respective get method
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters: none
     |
     |  Returns: the title of the task, the description the task, and the due date and time of the task
     *-------------------------------------------------------------------*/
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    /*---------------------------------------------------------------------
     |  Method toString
     |
     |  Purpose:  return the task as a formatted string to display to the user
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters: none
     |
     |  Returns: the task's title, description, and due date and time in the correct format as a string
     *-------------------------------------------------------------------*/
    @Override
    public String toString() {
        return title + "\n" + description + "\n" + dueDateTime.format(formatter) + "\n";
    }

    /*---------------------------------------------------------------------
         |  Method compareTo
         |
         |  Purpose: used to compare two tasks' due times to each other to easily sort tasks 
         |
         |  Pre-condition: none
         |
         |  Post-condition: none
         |
         |  Parameters:
         |	other -- the task to compare this task's time with
         |
         |  Returns: an int representing how this task's time compares to the other task's time
         |           0 - if the tasks have the same due time
         |           < 0 - if the task is due before the other task
         |           > 0 - if the task is due after the other task
         *-------------------------------------------------------------------*/
    @Override
    public int compareTo(Task other) {
        return dueDateTime.compareTo(other.dueDateTime);
    }

    /*---------------------------------------------------------------------
     |  Method fromString
     |
     |  Purpose: convert a formatted task title, description, and due date and time to a Task object
     |
     |  Pre-condition: the taskString is formatted correctly
     |
     |  Post-condition: none
     |
     |  Parameters: taskString - the formatted string representation of a task to convert
     |
     |  Returns: the converted Task object
     *-------------------------------------------------------------------*/
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
