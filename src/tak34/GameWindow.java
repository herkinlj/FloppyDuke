package tak34;

import javax.swing.*;


public class GameWindow
{
  public JFrame jFrame;
  public GameWindow(Game game)
  {
    jFrame = new JFrame("Floppy Duke");
    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    jFrame.add(game);
    jFrame.setLocationRelativeTo(null);
    jFrame.setResizable(false);
    jFrame.pack();
    jFrame.setVisible(true);
  }
}
