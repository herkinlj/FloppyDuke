package gameplay_no_multi;

import io.ResourceFinder;
import resources.Marker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;

public class BernySprite2
{
  private int x, dx, screen1, screen2;
  private int y, dy;
  private ResourceFinder resourceFinder;
  private Image bernyBooImg;
  private ImageIcon bernyBooIcon;

  public BernySprite2() throws IOException
  {
    resourceFinder = ResourceFinder.createInstance(Marker.class);
    InputStream ioStream = resourceFinder.findInputStream("Berny.png");
    bernyBooImg = ImageIO.read(ioStream);
    bernyBooImg = bernyBooImg.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
    bernyBooIcon = new ImageIcon(bernyBooImg);
    x = 0;
    screen1 = 0;
    y = 290;
    screen2 = 1280;

  }

  public void moveBernyHorizontal()
  {
    x = dx + x;
    y = dy + y;
    screen1 = screen1 + dx;
    screen2 = screen2 - dx;
  }

  public int getX()
  {
    return x;
  }

  public void setScreen2(int val)
  {
    screen2 = val;
  }
  public void setX(int val)
  {
    x = val;
  }
  public void setScreen1(int val)
  {
    screen1 = val;
  }

  public void setY(int val)
  {y = val;}
  public int getScreen2()
  {
    return screen2;
  }
  public int getScreen1()
  {
    return screen1;
  }
  public int getY()
  {
    return y;
  }

  public Image getBernyBooImg()
  {
    return bernyBooImg;
  }

  /**
   * @param e
   */
  public void KeyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();
    System.out.println("key pressed");
    if (key == KeyEvent.VK_LEFT && getX() > 0)
    {
      dx = -1;
    } else if (key == KeyEvent.VK_RIGHT)
    {
      dx = 1;
    }

  }

  /**
   *
    * @param e
   */
  public void KeyReleased(KeyEvent e)
  {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_LEFT)
    {
      dx = 0;
    } else if (key == KeyEvent.VK_RIGHT)
      dx = 0;
    }

  }

