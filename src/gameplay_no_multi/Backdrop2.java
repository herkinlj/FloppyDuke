package gameplay_no_multi;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Backdrop2
{
  public static final int WIDTH = 1280;
  public static final int HEIGHT = 540;
  public static void main(String[] args) throws IOException
  {
    new Backdrop2();
  }

  public Backdrop2() throws IOException
  {
    JFrame jfFrame = new JFrame("FloppyDukev3");
    jfFrame.setBounds(new Rectangle(WIDTH, HEIGHT));
    jfFrame.setResizable(false);
    jfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jfFrame.setLocationRelativeTo(null);
    Board board = new Board();
    jfFrame.add(new Board());
    board.requestFocusInWindow();
    jfFrame.setVisible(true);

  }
}
