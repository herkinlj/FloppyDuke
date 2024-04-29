package take3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gameplay.SunbeltSprite;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import io.ResourceFinder;
import resources.Marker;

public class GamePanel extends JPanel {

  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private ResourceFinder finder;
  private BufferedImage img;
  private BufferedImage[] headButt;
  private BufferedImage[] teams = new BufferedImage[13];
  private int animationTick, animationIndex, animationSpeed = 30;




  public GamePanel() throws IOException
  {
    finder = ResourceFinder.createInstance(Marker.class);
    InputStream io = finder.findInputStream("berny.png");
    try
    {
      img = ImageIO.read(io);
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    mouseInputs = new MouseInputs(this);
    setPanelSize();
    loadAnimations();
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);


  }

  public void loadAnimations() throws IOException
  {
   headButt = new BufferedImage[3];
   InputStream io = finder.findInputStream("berny_right_head.png");
   headButt[0] = img;
   headButt[1] = ImageIO.read(io);
   headButt[2] = img;
   for (int i = 0; i < teams.length; i++)
   {
     io = finder.findInputStream(SunbeltSprite.TEAM_NAMES[i]);
     teams[i] = ImageIO.read(io);
   }
  }

  public void setPanelSize()
  {
    Dimension dimension = new Dimension(1280, 800);
    setMinimumSize(dimension);
    setMinimumSize(dimension);
    setPreferredSize(dimension);
  }
  public void changeXDelta(int value) {
    this.xDelta += value;

  }

  public void changeYDelta(int value) {
    this.yDelta += value;

  }

  public void updateAnimationTick()
  {
    animationTick++;
    if (animationTick >= animationSpeed)
    {
      animationTick = 0;
      animationIndex++;
      if (animationIndex >= headButt.length)
      {
        animationIndex = 0;
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    updateAnimationTick();
    g.drawImage(headButt[animationIndex].getScaledInstance(100, 100, Image.SCALE_DEFAULT), (int) xDelta, (int) yDelta , null);

  }




  }


