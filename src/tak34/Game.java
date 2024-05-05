package tak34;
// GamePanel.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends JPanel implements KeyListener, Runnable
{
  private final int GROUND_LEVEL = 300;
  private final int PANEL_WIDTH = 800;
  private final int PANEL_HEIGHT = 400;
  private Sprite sprite;
  private List<GameObstacle> obstacles;
  private int score;
  private Random random = new Random();
  private boolean isRunning;

  public Game()
  {
    this.sprite = new Sprite(50, GROUND_LEVEL, 20, 20);
    this.obstacles = new ArrayList<>();
    this.score = 0;
    this.isRunning = true;

    setFocusable(true);
    addKeyListener(this);
  }

  public void startGameLoop()
  {
    new Thread(() -> {
      long lastObstacleTime = System.currentTimeMillis();
      while (isRunning)
      {
        // Update game objects
        sprite.update(GROUND_LEVEL);

        // Add new obstacles
        if (System.currentTimeMillis() - lastObstacleTime > 2000)
        {
          obstacles.add(new GameObstacle(PANEL_WIDTH, GROUND_LEVEL, 20, 20));
          lastObstacleTime = System.currentTimeMillis();
        }

        // Update obstacles and detect collisions
        for (int i = 0; i < obstacles.size(); i++)
        {
          GameObstacle obstacle = obstacles.get(i);
          obstacle.moveLeft();

          if (obstacle.isOutOfScreen())
          {
            obstacles.remove(i);
            score++;
            i--;
          }
          else if (sprite.isColliding(obstacle))
          {
            endGame();
            return;
          }
        }

        // Redraw the screen
        repaint();

        // Control the game loop speed
        try
        {
          Thread.sleep(30);
        }
        catch (InterruptedException e)
        {
          e.printStackTrace();
        }
      }
    }).start();
  }

  private void endGame()
  {
    isRunning = false;
    System.out.println("Game Over! Score: " + score);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.CYAN);
    g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

    g.setColor(Color.GREEN);
    g.fillRect(0, GROUND_LEVEL + 20, PANEL_WIDTH, 20);

    g.setColor(Color.BLACK);
    g.drawString("Score: " + score, 10, 10);

    sprite.draw(g);

    for (GameObstacle obstacle : obstacles)
    {
      obstacle.draw(g);
    }
  }

  @Override
  public void keyTyped(KeyEvent e)
  {
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_SPACE)
    {
      sprite.jump();
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
  }

  /**
   * When an object implementing interface {@code Runnable} is used to create a thread, starting the
   * thread causes the object's {@code run} method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method {@code run} is that it may take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run()
  {

    double timePerFrame = 1000000000.0 / FPS_SET;
    double timePerUpdate = 1000000000.0 / UPS_SET;

    long previousTime = System.nanoTime();

    int frames = 0;
    int updates = 0;
    long lastCheck = System.currentTimeMillis();

    double deltaU = 0;
    double deltaF = 0;

    while (true)
    {
      long currentTime = System.nanoTime();

      deltaU += (currentTime - previousTime) / timePerUpdate;
      deltaF += (currentTime - previousTime) / timePerFrame;
      previousTime = currentTime;

      if (deltaU >= 1)
      {
        update();
        updates++;
        deltaU--;
      }

      if (deltaF >= 1)
      {
        repaint();
        frames++;
        deltaF--;
      }

      if (System.currentTimeMillis() - lastCheck >= 1000)
      {
        lastCheck = System.currentTimeMillis();
        System.out.println("FPS: " + frames + " | UPS: " + updates);
        frames = 0;
        updates = 0;

      }

    }
  }
}
