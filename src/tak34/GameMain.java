package tak34;

// MainGame.java
import javax.swing.*;

public class GameMain {
  public static void main(String[] args) {
    JFrame frame = new JFrame("2D Sidescroller Game");
    Game panel = new Game();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 400);
    frame.add(panel);
    frame.setVisible(true);

    // Start the game loop
    panel.startGameLoop();
  }
}
