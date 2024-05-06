package tak34;

// Projectile.java
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Projectile {
  private int x, y;  // Position of the projectile
  private final int speed = 10;  // Speed at which the projectile moves
  private final int width = 20, height = 5;  // Size of the projectile
  private ArrayList<Projectile> projectiles;
  private boolean colorSwitch;
  public Projectile(int startX, int startY) {
    this.x = startX;
    this.y = startY;
    projectiles = new ArrayList<>();
    colorSwitch = false;
  }

  // Move the projectile to the right
  public void move() {
    x += speed;
  }

  // Check if the projectile is out of screen bounds
  public boolean isOutOfScreen(int panelWidth) {
    return x > panelWidth;
  }

  // Check if the projectile intersects with an obstacle
  public boolean intersects(GameObstacle obstacle) {
    return x < obstacle.getX() + obstacle.getWidth() &&
        x + width > obstacle.getX() &&
        y < obstacle.getY() + obstacle.getHeight() &&
        y + height > obstacle.getY();
  }

  // Draw the projectile
  public void draw(Graphics2D g) {
    if (colorSwitch)
    {
      g.setColor(new Color(0x4B006E));
      colorSwitch = false;
    }
    else
    {
      g.setColor(new Color(0xFFC400));
      colorSwitch = true;
    }
    g.fillRect(x, y, width, height);
  }

  public void shoot() {
    projectiles.add(new Projectile(x + width, y + height / 2));  // Start from the right side
  }

  // Update all projectiles
  public void updateProjectiles(int panelWidth) {
    for (int i = 0; i < projectiles.size(); i++) {
      Projectile proj = projectiles.get(i);
      proj.move();
      if (proj.isOutOfScreen(panelWidth)) {
        projectiles.remove(i);
        i--;  // Adjust the index after removal
      }
    }
  }

  // Get the list of projectiles (for collision detection)
  public ArrayList<Projectile> getProjectiles() {
    return projectiles;
  }
}

