package tak34;
import java.awt.*;
public class GameObstacle
{
  // Obstacle.java

    private int x, y, width, height;
    private final int speed = 5;

    public GameObstacle(int x, int y, int width, int height) {
      this.x = x;
      this.y = y - height;
      this.width = width;
      this.height = height;
    }

    public void moveLeft() {
      x -= speed;
    }

    public boolean isOutOfScreen() {
      return x < -width;
    }

    public void draw(Graphics g) {
      g.setColor(Color.RED);
      g.fillRect(x, y, width, height);
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


