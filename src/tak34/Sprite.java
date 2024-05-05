package tak34;

// Sprite.java
import java.awt.*;

public class Sprite {
  private int x, y, width, height;
  private double velocityY = 0;
  private static final double GRAVITY = 0.5;
  private static final double JUMP_STRENGTH = -10;

  public Sprite(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public void jump() {
    if (y == 300) {
      velocityY = JUMP_STRENGTH;
    }
  }

  public void update(int groundLevel) {
    velocityY += GRAVITY;
    y += velocityY;

    if (y > groundLevel) {
      y = groundLevel;
      velocityY = 0;
    }
  }

  public void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillRect(x, y, width, height);
  }

  public boolean isColliding(GameObstacle obstacle) {
    Rectangle spriteBounds = new Rectangle(x, y, width, height);
    Rectangle obstacleBounds = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
    return spriteBounds.intersects(obstacleBounds);
  }
}
