package tak34;
import java.awt.*;
import java.util.Random;

public class GameObstacle
{
  // Obstacle.java

    private int x, y, width, height;
    private int speed;
    private Random random;
    private Image img;

    public GameObstacle(int x, int y, int width, int height, Image image) {
      img = image;
      random = new Random();
      this.x = x;
      this.y = y - random.nextInt(250);
      this.width = width + 60;
      this.height = height + 40;
      speed = 5;
     // this.speed = speed;
    }

    public void moveLeft(float speedFactor) {
      x -= (int) (speed * speedFactor);
    }

    public boolean isOutOfScreen() {
      return x < -width;
    }

    public void draw(Graphics g) {

      g.drawImage(img, x, y, width, height, null);
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getWidth() {
      return width;
    }

    public int getHeight() {
      return height;
    }
  }


