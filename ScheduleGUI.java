import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScheduleGUI extends JFrame {
  //useful constants that might be used later
  private final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
  private final GraphicsDevice device = env.getDefaultScreenDevice();
  
  public ScheduleGUI() {
    super();
    setResizable(false);
    setFocusable(true);
  }

  /**
   * Creates the window for the GUI
   * @param title of the window
  */
  public void createWindow(String title) { //doesnt take in width or height because it's intended to be fullscreen
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(960, 540);
    setTitle(title);
    device.setFullScreenWindow(this);

    // Set layout to null so i can position gui components wherever
    setLayout(null);
  }

  public void createButton(String text, int x, int y, int width, int height, ActionEvent e) {
    JButton button = new JButton(text);
    button.setBounds(x, y, width, height);
    button.addActionListener(action);
    add(button);
  }
}