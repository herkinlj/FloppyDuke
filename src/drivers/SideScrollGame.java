package drivers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Liam J Herkins
 */
public class SideScrollGame extends JPanel implements ActionListener, KeyListener
{
  private final int WINDOW_WIDTH = 800;
  private final int WINDOW_HEIGHT = 400;
  private final int GROUND_HEIGHT = 300;
  private final int PLAYER_WIDTH = 40;
  private final int PLAYER_HEIGHT = 40;
  private final int TOWER_WIDTH = 30;
  private final int TOWER_HEIGHT = 50;

  private Timer timer;
  private int playerX = 100;
  private int playerY = GROUND_HEIGHT - PLAYER_HEIGHT;
  private boolean isJumping = false;
  private int jumpSpeed = 0;

  private List<Rectangle> towers;
  private int score = 0;

  private JFrame frame;
  private JPanel menuPanel;
  private JButton startButton, instructionsButton, exitButton;

  public SideScrollGame()
  {
    frame = new JFrame("FloppyDuke");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setupMenu();
    frame.setVisible(true);
  }

  private void setupMenu()
  {
    menuPanel = new JPanel(new GridLayout(3, 1));
    startButton = new JButton("Start Game");
    instructionsButton = new JButton("Instructions");
    exitButton = new JButton("Exit");

    menuPanel.add(startButton);
    menuPanel.add(instructionsButton);
    menuPanel.add(exitButton);

    frame.add(menuPanel);

    startButton.addActionListener(e -> startGame());
    instructionsButton.addActionListener(e -> showInstructions());
    exitButton.addActionListener(e -> System.exit(0));
  }

  private void startGame()
  {
    frame.remove(menuPanel);
    this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    this.setBackground(Color.CYAN);
    this.setFocusable(true);
    setupKeyBindings();  // Set up key bindings for control

    towers = new ArrayList<>();
    towers.add(
        new Rectangle(WINDOW_WIDTH, GROUND_HEIGHT - TOWER_HEIGHT, TOWER_WIDTH, TOWER_HEIGHT));

    timer = new Timer(30, this);
    timer.start();

    frame.add(this);
    frame.pack();
    frame.revalidate();
  }

  public void setupKeyBindings()
  {
    this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "jump");
    this.getActionMap().put("jump", new AbstractAction()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        if (!isJumping)
        {
          isJumping = true;
          jumpSpeed = -15;
        }
      }
    });
  }

  private void showInstructions()
  {
    JOptionPane.showMessageDialog(frame,
        "Use SPACE to jump over the towers and avoid collisions. Try to get the highest score!");
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.GREEN);
    g.fillRect(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT);

    g.setColor(Color.RED);
    for (Rectangle tower : towers)
    {
      g.fillRect(tower.x, tower.y, tower.width, tower.height);
    }

    g.setColor(Color.BLACK);
    g.drawString("Score: " + score, 10, 20);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (isJumping)
    {
      playerY += jumpSpeed;
      jumpSpeed += 1;
      if (playerY >= GROUND_HEIGHT - PLAYER_HEIGHT)
      {
        playerY = GROUND_HEIGHT - PLAYER_HEIGHT;
        isJumping = false;
      }
    }

    Iterator<Rectangle> iter = towers.iterator();
    while (iter.hasNext())
    {
      Rectangle tower = iter.next();
      tower.x -= 10;

      if (tower.x + TOWER_WIDTH < 0)
      {
        tower.x = WINDOW_WIDTH;
        score++;
      }

      if (tower.intersects(
          new Rectangle(playerX, playerY, PLAYER_WIDTH, PLAYER_HEIGHT)) && !isJumping)
      {
        score--;  // Penalty for hitting a tower
        tower.x = WINDOW_WIDTH;
      }
    }
    repaint();
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumping)
    {
      isJumping = true;
      jumpSpeed = -15;
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
  }

  @Override
  public void keyTyped(KeyEvent e)
  {
  }

  public static void main(String[] args)
  {
    new SideScrollGame();  // Create and show the menu
  }
}
