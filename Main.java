/*=============================================================================
 |   Assignment:  Program #1:  White Tower Project
 |       Author:  Kaden Bautista 201199540@lbschools.net
 |      Partner:  Neil Marquez 201209850@lbschools.net
 |
 |  Course Name:  AP Computer Science A
 |   Instructor:  Mr. Virak
 |     Due Date:  5/31/24 11:59pm
 |
 |  Description:  The goal of this program is to provide a way for people to keep track of
 |                all the tasks they have to do whilst providing and easy-to-use user interface.
 |
 |     Language:  Java 17
 | Ex. Packages:  javax.swing
 |                java.io
 |                java.time
 |                java.util
 |                java.awt
 |                
 | Deficiencies:  There are some red underlines because of the Java version that the replit autocorrect recognizes.
 |                However, those don't cause any actual issues and the project still runs. There is another error in
 |                which I added a lot of tasks and the "task added" display displayed a blank display, but it still
 |                functions as you can still close the display. We did not use nested itekrations in the program.
 *===========================================================================*/

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

        //title
        JLabel titleLabel = gui.createLabel("Title:", 50, 50, 100, 30);
        JTextField titleField = gui.createTextField(100, 50, 200, 30);

        //description
        JLabel descriptionLabel = gui.createLabel("Description:", 50, 100, 100, 30);
        JTextArea descriptionArea = gui.createTextArea(150, 100, 200, 100, true);

        //time
        JLabel timeLabel = gui.createLabel("Due Time (yyyy-MM-dd HH:mm):", 50, 220, 250, 30);
        JTextField timeField = gui.createTextField(300, 220, 200, 30);

        //schedule
        JTextArea taskDisplayArea = gui.createTextArea(50, 350, 500, 200, false);
        JScrollPane scrollbar = gui.createScrollbar(taskDisplayArea, 50, 350, 500, 200);

        //buttons
        JButton taskButton = gui.createButton("Add Task", 50, 280, 150, 40, e -> gui.addTask(titleField.getText(), descriptionArea.getText(), timeField.getText(), taskDisplayArea));
        JButton saveButton = gui.createButton("Save Schedule", 220, 280, 150, 40, e -> gui.saveSchedule());
        JButton loadButton = gui.createButton("Load Schedule", 390, 280, 150, 40, e -> gui.loadSchedule(taskDisplayArea));
    }
}