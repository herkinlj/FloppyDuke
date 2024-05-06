package tak34;

// Sprite.java

import io.ResourceFinder;
import resources.Marker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Sprite
{
  private int x, y, width, height;
  private double velocityY = 0;
  private static final double GRAVITY = 0.5;
  private static final double JUMP_STRENGTH = -10;
  private Image berny;
  private ResourceFinder resourceFinder;
  public int jumpCount;
  public boolean canJump;
  private static final int MAX_JUMP_COUNT = 4;


  public Sprite(int x, int y, int width, int height) throws IOException
  {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    jumpCount = 0;
    resourceFinder = ResourceFinder.createInstance(Marker.class);
    InputStream inputStream = resourceFinder.findInputStream("berny.png");
    berny = ImageIO.read(inputStream);
    canJump = true;
  }
  public void resetPosition()
  {
    this.x = 50;
    this.y = 460;
  }

  public void jump()
  {
    if (canJump) { // Check if the sprite can currently jump
      if (y == 460 || jumpCount < MAX_JUMP_COUNT) { // Allow jumping only if on the ground or within max jump count
        velocityY = JUMP_STRENGTH;
        jumpCount++;
      }
      if (jumpCount >= MAX_JUMP_COUNT) { // Disable jumping if max jump count reached
        canJump = false;
      }
    }
  }
  public void update(int groundLevel)
  {
    velocityY += GRAVITY;
    y += velocityY;

    if (y > groundLevel)
    {
      y = groundLevel;
      velocityY = 0;
      jumpCount = 0; // Reset jump count when landing on the ground
      canJump = true; // Allow jumping again when landing on the ground
    }
  }

  public void draw(Graphics g)
  {
    g.drawImage(berny, x, y, width, height, null);
  }

  public boolean isColliding(GameObstacle obstacle)
  {
    Rectangle spriteBounds = new Rectangle(x, y, width - 20, height - 20);
    Rectangle obstacleBounds = new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(),
        obstacle.getHeight());
    return spriteBounds.intersects(obstacleBounds);
  }

  public int getX()
  {
    return x;
  }

  public int getY()
  {
    return y;
  }
}
