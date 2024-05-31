import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScheduleGUI extends JFrame {
  //device variables
  private final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
  private final GraphicsDevice device = env.getDefaultScreenDevice();

  //ui variables
  private JTextField titleField;
  private JTextArea descriptionArea;
  private JTextField timeField;

  //keep track of tasks
  private final ArrayList<Task> tasks = new ArrayList<>();
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  private JTextArea taskDisplayArea;

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

  // Title input
  JLabel titleLabel = createLabel("Title:", 50, 50, 100, 30);
  titleField = createTextField(150, 50, 200, 30);

  // Description input
  JLabel descriptionLabel = createLabel("Description:", 50, 100, 100, 30);
  descriptionArea = createTextArea(150, 100, 200, 100, true);

  // Due time input
  JLabel timeLabel = createLabel("Due Time (yyyy-MM-dd HH:mm):", 50, 220, 250, 30);
  timeField = createTextField(300, 220, 200, 30);

  // create buttons for user to interact with
  createButton("Add Task", 50, 280, 150, 40, e -> addTask());
  createButton("Save Schedule", 220, 280, 150, 40, e -> saveSchedule());
  createButton("Load Schedule", 390, 280, 150, 40, e -> loadSchedule());

  // Task display area
  taskDisplayArea = createTextArea(50, 350, 500, 200, false);

  revalidate();
  repaint();
  }

  //ui methods
  private JButton createButton(String text, int x, int y, int width, int height, ActionListener action) {
    JButton button = new JButton(text);
    button.setBounds(x, y, width, height);
    button.addActionListener(action);
    add(button);
    return JButton
  }
  private JLabel createLabel(String text, int x, int y, int width, int height) {
    JLabel label = new JLabel(text);
    label.setBounds(x, y, width, height);
    add(label);
    return label;
  }
  private JTextField createTextField(int x, int y, int width, int height) {
    JTextField textField = new JTextField();
    textField.setBounds(x, y, width, height);
    add(textField);
    return textField;
  }
  private JTextArea createTextArea(int x, int y, int width, int height, boolean editable) {
    JTextArea textArea = new JTextArea();
    textArea.setBounds(x, y, width, height);
    textArea.setEditable(editable);
    add(textArea);
    return textArea;
  }

  //user methods
  private void addTask() {
    String title = titleField.getText();
    String description = descriptionArea.getText();
    String timeStr = timeField.getText();

    try {
      LocalDateTime dueDateTime = LocalDateTime.parse(timeStr, formatter);
      Task task = new Task(title, description, dueDateTime);
      tasks.add(task);
      updateTaskDisplay();
      JOptionPane.showMessageDialog(this, "Task added successfully.");
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Invalid date/time format.");
    }
  }
  private void updateTaskDisplay() {
    StringBuilder taskList = new StringBuilder();
    for (Task task : tasks) {
      taskList.append(task.toString()).append("\n");
    }
    taskDisplayArea.setText(taskList.toString());
  }
  private void saveSchedule() {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("schedule.txt"))) {
      for (Task task : tasks) {
        writer.write(task.toString());
        writer.write("=====\n");
      }
      JOptionPane.showMessageDialog(this, "Schedule saved successfully.");
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Failed to save schedule.");
    }
  }
  public void loadSchedule() {
    tasks.clear();
    try (BufferedReader reader = new BufferedReader(new FileReader("schedule.txt"))) {
      StringBuilder taskString = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.equals("=====")) {
          Task task = Task.fromString(taskString.toString());
          tasks.add(task);
          taskString.setLength(0); // Clear the string builder
        } else {
          taskString.append(line).append("\n");
        }
      }
      updateTaskDisplay();
      JOptionPane.showMessageDialog(this, "Schedule loaded successfully.");
    } catch (IOException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(this, "Failed to load schedule.");
    }
  }
}
