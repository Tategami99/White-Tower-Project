/*
* Programmer(s): Kaden Bautista and Neil Marquez
* Date: 5/31/24
* Section: Period 2
* Assignment: White Tower Project
*/
/*
We are proposing to create a schedule planner where you can add things, set goals, and have reminders for deadlines. The program will have a UI. This helps our school be more productive and can be used by other schools as well.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
public class Main{
    public static void main(String[] args) {
        System.out.println("hi world");
        ScheduleGUI gui = new ScheduleGUI();
        gui.createWindow("Schedule App");

        // //labels
        // gui.createLabel("Title:", 50, 50, 100, 30);
        // gui.createLabel("Description:", 50, 100, 100, 30);
        // gui.createLabel("Due DateTime (yyyy-MM-dd HH:mm):", 50, 220, 250, 30);

        // //text boxes
        // JTextField titleField = gui.createTextField(150, 50, 200, 30);
        // JTextField dueField = gui.createTextField(300, 220, 200, 30);

        // //text areas
        // gui.createTextArea(50, 350, 500, 200);
        // JTextArea descriptionArea = gui.createTextArea(150, 100, 200, 100);
        
        // //buttons
        // gui.createButton("Add Task", 50, 280, 150, 40, e -> gui.addTask(titleField.getText(), descriptionArea.getText(), dueField.getText()));
        // gui.createButton("Save Schedule", 220, 280, 150, 40, e -> gui.saveSchedule());
        // gui.createButton("Load Schedule", 390, 280, 150, 40, e -> gui.loadSchedule());
    }
}