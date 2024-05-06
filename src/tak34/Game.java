package tak34;
import io.ResourceFinder;
import resources.Marker;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.Instant;

public class Game extends JPanel implements KeyListener {
  private long startTimeMillis;  // Variable to store the game start time in milliseconds
  private long elapsedTimeMillis;  // Variable to store the elapsed time
  private Font minecraft;
  private final int GROUND_LEVEL = 440;
  private final int PANEL_WIDTH = 900;
  private final int PANEL_HEIGHT = 580;
  private Sprite sprite;
  private List<GameObstacle> obstacles;
  private int score;
  private Random random = new Random();
  private boolean isRunning;
  private boolean isGameLoopRunning; // Variable to track if the game loop is running
  private ResourceFinder resourceFinder;
  private Image background;
  private ArrayList<BufferedImage> sunbeltTeams;
  private String[] teamNames = {"app_state", "ark_state", "cajuns", "coastal", "georgia_south",
      "georgia_st", "marshall", "odu", "south_bama", "south_miss", "texas_state", "troy", "ulm"};
  private Image ground;
  private Image scoreBoard;
  private Clip backgroundMusic;
  private StartMenu menu;
  private float obstacleSpeedFactor;

  public Game() throws IOException, FontFormatException
  {
    setPanelSize();
    setFocusable(true);
    resourceFinder = ResourceFinder.createInstance(Marker.class);
    sunbeltTeams = new ArrayList<>();
    initSunbeltTeams();
    initGroundScore();
    this.sprite = new Sprite(50, GROUND_LEVEL, 50, 50);
    this.obstacles = new ArrayList<>();
    this.score = 0;
    this.isRunning = true;
    this.isGameLoopRunning = false; // Initialize to false
    minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\The Herks\\IdeaProjects\\FloppyDuke\\src\\resources\\Minecraft.ttf"));
    minecraft = minecraft.deriveFont(24f);
    background = ImageIO.read(resourceFinder.findInputStream("wilson_hall_snes_vivid_roof.png"));

    addKeyListener(this);
  }

  private void loadBackgroundMusic() {
    try {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
          resourceFinder.findInputStream("game_music.wav"));
      backgroundMusic = AudioSystem.getClip();
      backgroundMusic.open(audioInputStream);
      backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  private void playBackgroundMusic() {
    if (backgroundMusic != null && !backgroundMusic.isRunning()) {
      backgroundMusic.start();
    }
  }

  private void stopBackgroundMusic() {
    if (backgroundMusic != null && backgroundMusic.isRunning()) {
      backgroundMusic.stop();
    }
  }

  public void initSunbeltTeams() throws IOException {
    try {
      InputStream inputStream;
      for (String str : teamNames) {
        str += ".png";
        inputStream = resourceFinder.findInputStream(str);
        sunbeltTeams.add(ImageIO.read(inputStream));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void initGroundScore() throws IOException {
    InputStream ioStream = resourceFinder.findInputStream("8bitground.jpg");
    ground = ImageIO.read(ioStream);
    ioStream = resourceFinder.findInputStream("score.png");
    scoreBoard = ImageIO.read(ioStream);
  }

  public void setPanelSize() {
    Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    setMinimumSize(dimension);
    setMinimumSize(dimension);
    setPreferredSize(dimension);
  }

  public void startGameLoop() {
    startTimeMillis = System.currentTimeMillis();
    if (!isGameLoopRunning) { // Check if the game loop is not already running
      loadBackgroundMusic(); // Load background music but don't play yet

      isGameLoopRunning = true;

      new Thread(() -> {
        long lastObstacleTime = System.currentTimeMillis();
        int updatesPerSecond = 60;
        int framesPerSecond = 60;
        long updateInterval = 1000 / updatesPerSecond;
        long frameInterval = 1000 / framesPerSecond;
        long lastUpdateTime = System.currentTimeMillis();
        long lastFrameTime = System.currentTimeMillis();

        while (isRunning) {
          long currentTime = System.currentTimeMillis();
          playBackgroundMusic();
          obstacleSpeedFactor = 1.0f + ((float) elapsedTimeMillis / 10000L);
          if (currentTime - lastUpdateTime >= updateInterval) {
            sprite.update(GROUND_LEVEL);

            if (System.currentTimeMillis() - lastObstacleTime > 2000) {
              obstacles.add(new GameObstacle(PANEL_WIDTH, GROUND_LEVEL, 20, 20,
                  sunbeltTeams.get(random.nextInt(0, 12))));
              lastObstacleTime = System.currentTimeMillis();
            }

            for (int i = 0; i < obstacles.size(); i++) {
              GameObstacle obstacle = obstacles.get(i);
              obstacle.moveLeft(obstacleSpeedFactor);

              if (obstacle.isOutOfScreen()) {
                obstacles.remove(i);
                score++;
                i--;
              } else if (sprite.isColliding(obstacle)) {
                endGame();
                break;
              }
            }
            lastUpdateTime = currentTime;
          }

          if (currentTime - lastFrameTime >= frameInterval) {
            repaint();
            lastFrameTime = currentTime;
          }

          try {
            Thread.sleep(5);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        // Set isGameLoopRunning to false after the game loop ends
        isGameLoopRunning = false;
      }).start();
    }
  }

  private void endGame() {
    isRunning = false;
    stopBackgroundMusic(); // Stop background music
    int choice = JOptionPane.showConfirmDialog(this, "Game Over! Score: " + score + ". Do you want to restart the game?", "Game Over", JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION) {
      restartGame();
    } else {
      System.exit(0); // Exit the game if the player chooses not to restart
    }
  }

  private void restartGame() {
    obstacles.clear();
    sprite.resetPosition(); // Assuming you have a method to reset the sprite's position
    score = 0;
    isRunning = true;
    startGameLoop();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;



    g2.drawImage(background, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

    g2.drawImage(ground.getScaledInstance(PANEL_WIDTH, 100, Image.SCALE_DEFAULT), 0,
        GROUND_LEVEL + 30, null);

    g2.drawImage(scoreBoard.getScaledInstance(100, 20, Image.SCALE_DEFAULT), 10, 10, null);
    g2.setColor(new Color(0x4B006E));
    g2.setFont(minecraft);

    g2.drawString(String.format("%d", score), 120, 27);
    if (elapsedTimeMillis < 4000L)
      g2.drawString("Press Enter to Jump over Sunbelt foes", 250, 100);
    sprite.draw(g2);

    for (GameObstacle obstacle : obstacles) {
      obstacle.draw(g2);
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      sprite.jump();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }
}
