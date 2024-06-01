import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;

/*+----------------------------------------------------------------------
 ||
 ||  Class ScheduleGUI
 ||
 ||         Author:  Kaden Bautista & Neil Marquez
 ||
 ||        Purpose:  This class exists to create the user interface for the schedule app.
 ||                  It creates the window, text, buttons, and all the visual elements for the app.
 ||                  It also contains methods to save tasks for later use or load tasks from previous
 ||                  uses by reading and writing to a file.
 ||
 ||  Inherits From:  JFrame
 ||
 ||     Interfaces:  none
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  no public constants
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  Default constructor, no arguments
 ||
 ||  Class Methods:  none
 ||
 ||  Inst. Methods:  createButton, 1 String arugment & 4 int arguments & 1 ActionListener argument, returns a JButton
 ||                  createLabel, 1 String argument & 4 int arguments, returns a JLabel
 ||                  createTextField, 4 int arguments, returns a JTextField
 ||                  createTextArea, 4 int arguments & 1 boolean argument, returns a JTextArea
 ||                  createScrollbar, 1 JTextArea argument & 4 int arguments, returns a JScrollPane
 ||                  saveSchedule, no arguments, returns nothing
 ||                  loadSchedule, JTextArea argument, returns nothing
 ||
 ++-----------------------------------------------------------------------*/
public class ScheduleGUI extends JFrame {
  //device variables
  private final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
  private final GraphicsDevice device = env.getDefaultScreenDevice();

  //keep track of tasks
  private final ArrayList<Task> tasks = new ArrayList<>();

  public ScheduleGUI() {
    super();
    setResizable(false);
    setFocusable(true);
  }

  public void createWindow(String title) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle(title);
    setUndecorated(true);
    setVisible(true);
    device.setFullScreenWindow(this);
  
    setLayout(null); // absolute positioning
  }

  /*---------------------------------------------------------------------
   |  Method createButton
   |
   |  Purpose: add a button to the window
   |
   |  Pre-condition: none
   |
   |  Post-condition: none
   |
   |  Parameters:
   |	text -- text displayed on the button
   |	x -- x position(px)
   |	y -- y position(px)
   |	width -- width of button(px)
   |	height -- height of button(px)
   |	action -- action to perform when button clicked
   |
   |  Returns:  JButton - the button created
   *-------------------------------------------------------------------*/
  public JButton createButton(String text, int x, int y, int width, int height, ActionListener action) {
    JButton button = new JButton(text);
    button.setBounds(x, y, width, height);
    button.addActionListener(action);
    add(button);
    revalidate();
    repaint();
    return button;
  }

  /*---------------------------------------------------------------------
     |  Method createLabel
     |
     |  Purpose: add a label/text to the window
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	text -- text to display
     |	x -- x position(px)
     |	y -- y position(px)
     |	width -- width of label(px)
     |	height -- height of label(px)
     |
     |  Returns:  JLabel - the label created
     *-------------------------------------------------------------------*/
  public JLabel createLabel(String text, int x, int y, int width, int height) {
    JLabel label = new JLabel(text);
    label.setBounds(x, y, width, height);
    add(label);
    revalidate();
    repaint();
    return label;
  }

  /*---------------------------------------------------------------------
     |  Method createTextField
     |
     |  Purpose: add an editable text field to the window
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	x -- x position(px)
     |	y -- y position(px)
     |	width -- width of text field(px)
     |	height -- height of text field(px)
     |
     |  Returns:  JTextField - the text field created
     *-------------------------------------------------------------------*/
  public JTextField createTextField(int x, int y, int width, int height) {
    JTextField textField = new JTextField();
    textField.setBounds(x, y, width, height);
    add(textField);
    revalidate();
    repaint();
    return textField;
  }

  /*---------------------------------------------------------------------
     |  Method createTextArea
     |
     |  Purpose: add a text area to the window
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	x -- x position(px)
     |	y -- y position(px)
     |	width -- width of text area(px)
     |	height -- height of text area(px)
     |  editable -- whether or not the text in the area can be changed by the user
     |
     |  Returns:  JTextArea - the text area created
     *-------------------------------------------------------------------*/
  public JTextArea createTextArea(int x, int y, int width, int height, boolean editable) {
    JTextArea textArea = new JTextArea();
    textArea.setBounds(x, y, width, height);
    textArea.setEditable(editable);
    add(textArea);
    revalidate();
    repaint();
    return textArea;
  }

  /*---------------------------------------------------------------------
     |  Method createScrollbar
     |
     |  Purpose: add a scrollbar to a text area
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	textArea -- the text area to add the scrollbar to
     |	x -- x position(px)
     |	y -- y position(px)
     |	width -- width of the text area the scrollbar will be on(px)
     |	height -- height of text area the scrollbar will be on(px)
     |
     |  Returns:  JScrollPane - the scrollbar created
     *-------------------------------------------------------------------*/
  public JScrollPane createScrollbar(JTextArea textArea, int x, int y, int width, int height) {
    JScrollPane scrollPane = new JScrollPane(textArea);
    scrollPane.setBounds(x, y, width, height);
    add(scrollPane);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    revalidate();
    repaint();
    return scrollPane;
  }

  /*---------------------------------------------------------------------
     |  Method addTask
     |
     |  Purpose: attempts to add a task to the schedule 
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	title -- the title of the task
     |	description -- the description of the task
     |	timeStr -- the time the task is due as a string
     |	displayArea -- the text area to display the tasks in
     |
     |  Returns:  none
     *-------------------------------------------------------------------*/
  public void addTask(String title, String description, String timeStr, JTextArea displayArea) {
    try {
      LocalDateTime dueDateTime = LocalDateTime.parse(timeStr, Task.formatter);
      Task task = new Task(title, description, dueDateTime);
      tasks.add(task);
      updateTaskDisplay(displayArea);
      JOptionPane.showMessageDialog(this, "Task added successfully.");
    } catch (Exception e) {
      System.out.println(timeStr);
      JOptionPane.showMessageDialog(this, "Invalid date/time format.");
    }
  }

  /*---------------------------------------------------------------------
     |  Method updateTaskDisplay
     |
     |  Purpose: updates the text area to display all the tasks in the schedule sorted by the closest due date and time
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	displayArea -- the text area to display the tasks in
     |
     |  Returns:  none
     *-------------------------------------------------------------------*/
  private void updateTaskDisplay(JTextArea displayArea) {
    Collections.sort(tasks);
    String taskList = "";
    for (Task task : tasks) {
        taskList += task.toString() + "\n";
    }
    displayArea.setText(taskList);
  }

  /*---------------------------------------------------------------------
     |  Method saveSchedule
     |
     |  Purpose: saves the tasks in the schedule to a file
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters: none
     |
     |  Returns:  none
     *-------------------------------------------------------------------*/
  public void saveSchedule() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("schedule.txt"))) {
      for (Task task : tasks) {
        writer.write(task.toString());
        writer.write("=====\n");
      }
      JOptionPane.showMessageDialog(this, "Schedule saved successfully.");
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Failed to save schedule.");
    }
  }

  /*---------------------------------------------------------------------
     |  Method loadSchedule
     |
     |  Purpose: loads the tasks from a file into the schedule and displays them on a text area
     |
     |  Pre-condition: none
     |
     |  Post-condition: none
     |
     |  Parameters:
     |	displayArea -- the text area to display the tasks on
     |
     |  Returns:  none
     *-------------------------------------------------------------------*/
  public void loadSchedule(JTextArea displayArea) {
    tasks.clear();
    try (BufferedReader reader = new BufferedReader(new FileReader("schedule.txt"))) {
      String taskString = "";
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.equals("=====")) {
          Task task = Task.fromString(taskString);
          tasks.add(task);
          taskString = "";
        } else {
          taskString += line + "\n";
        }
      }
      updateTaskDisplay(displayArea);
      JOptionPane.showMessageDialog(this, "Schedule loaded successfully.");
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, "Failed to load schedule.");
    }
  }
  
}
