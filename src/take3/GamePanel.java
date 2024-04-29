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
import static utils.Constants.*;
import static utils.Constants.PlayerConstants.GetSpriteAmount;

import resources.Marker;

public class GamePanel extends JPanel {

  private MouseInputs mouseInputs;
  private float xDelta = 100, yDelta = 100;
  private ResourceFinder finder;
  private BufferedImage img;
  private BufferedImage[][] animations;
  private BufferedImage[] teams = new BufferedImage[13];
  private int animationTick, animationIndex, animationSpeed = 30;
  private int playerAction;





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
//    animations = new BufferedImage[3][3];
//    for (int i = 0; i < animations.length; i++)
//    {
//      for (int j = 0; j < animations[i].length; j  ++)
//      {
//       // animations[i][j]
//      }
//    }
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
      if (animationIndex >= GetSpriteAmount(playerAction))
      {
        animationIndex = 0;
      }
    }
  }
  public void updateGame() {

    updateAnimationTick();

  }


  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    updateAnimationTick();
    g.drawImage(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT), (int) xDelta, (int) yDelta , null);

  }




  }


