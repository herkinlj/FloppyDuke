package gameplay_no_multi;

import io.ResourceFinder;
import resources.Marker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Board extends JPanel implements ActionListener
{
  private BernySprite2 berny;
  private Image bufferedImage;
  private ResourceFinder finder;
  private Timer timer;
  public Board() throws IOException
  {
    super(true);
    setBounds(new Rectangle(Backdrop2.WIDTH, Backdrop2.HEIGHT));
    berny = new BernySprite2();
    addKeyListener(new AL());
    setFocusable(true);
    finder = ResourceFinder.createInstance(Marker.class);
    InputStream ioStream = finder.findInputStream("Summer4.png");
    Image tempImg = ImageIO.read(ioStream);
    tempImg = tempImg.getScaledInstance(Backdrop2.WIDTH, Backdrop2.HEIGHT, Image.SCALE_DEFAULT);
    bufferedImage = tempImg;

    timer = new Timer(5, this);
    timer.start();



  }

  /**
   * Invoked when an action occurs.
   *
   * @param e
   *     the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {

    berny.moveBernyHorizontal();
    repaint();
  }

  public void paint(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    super.paint(g);
    // magic number is 1285

//    if ((berny.getX() - 381) % 1661 == 0)
//    {
//      System.out.println("reached first drop" + berny.getX());
//      berny.setNx(0);
//     // g2.drawImage(bufferedImage, (2154) -berny.getNx2(), 0, null);
//    }
//    if ((berny.getX() - Backdrop2.WIDTH) % 1661 == 0)
//    {
//      System.out.println("reached second drop " + berny.getX());
//      berny.setNx2(0);
//    }

    if (berny.getScreen1() == 1285)
    {
      System.out.println("balls");
      berny.setScreen1(0);
    }
    if (berny.getScreen2() == 0)
    {
      System.out.println("bigger balls");
      berny.setScreen2(1285);
    }
    g2.drawImage(bufferedImage, -berny.getScreen1(), 0, null);
    g2.drawImage(bufferedImage, berny.getScreen2(), 0, null);
    System.out.println("screen1 is at " + berny.getScreen1() + "screen 2 is at " + berny.getScreen2());
//    if (berny.getX() >= 381)
//    {
//      g2.drawImage(bufferedImage, (1280) - berny.getNx(),0 , this);ßß





//    }
    System.out.println(berny.getX());
    g2.drawImage(berny.getBernyBooImg(), 15, berny.getY(), null);


  }

  private class AL extends KeyAdapter {
    public void keyReleased(KeyEvent e)
    {
      berny.KeyReleased(e);
    }
    public void keyPressed(KeyEvent e)
    {
      berny.KeyPressed(e);
    }
  }

}
