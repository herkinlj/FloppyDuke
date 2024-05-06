package tak34;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApp extends JFrame {
  private StartMenu startMenu;
  private Game game;

  public MainApp() throws IOException, FontFormatException {
    // Initialize JFrame
    setTitle("Floppy Duke");
    setSize(900, 580);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    // Initialize StartMenu and Game panels
    startMenu = new StartMenu();
    game = new Game();

    // Initially show the StartMenu
    startMenu.requestFocusInWindow();
    add(startMenu, BorderLayout.CENTER);

    // Allow StartMenu to communicate with MainApp
    startMenu.setSwitcher(this);

    // Make the JFrame visible
    setVisible(true);
  }

  // Method to switch to the Game panel
  public void startGame() {
    // Remove the StartMenu
    remove(startMenu);

    // Add the Game panel and start its loop
    add(game, BorderLayout.CENTER);
    game.setFocusable(true);
    game.requestFocusInWindow();  // Ensure the Game panel receives keyboard focus

    // Start the Game panel's loop explicitly
    game.startGameLoop();

    // Refresh the JFrame
    revalidate();
    repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        new MainApp();
      } catch (IOException | FontFormatException e) {
        e.printStackTrace();
      }
    });
  }
}
