package driver;

import app.JApplication;
import gameplay.Backdrop;
import gameplay.SunbeltSprite;
import io.ResourceFinder;
import resources.Marker;
import visual.VisualizationView;
import visual.dynamic.described.TweeningSprite;
import visual.statik.sampled.ContentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class FloppyDriver extends JApplication
{
  public static final int WIDTH = 1720;
  public static final int HEIGHT = 720;
  private ResourceFinder resourceFinder;
  private ContentFactory contentFactory;

  public FloppyDriver(String[] args)
  {
    super(args, WIDTH, HEIGHT);
  }

  /**
   *
   */
  @Override
  public void init()
  {
    JPanel contentPane = (JPanel) getContentPane();
    contentPane.setLayout(null);
    contentPane.setBounds(200, 20, WIDTH, HEIGHT);
    resourceFinder = ResourceFinder.createInstance(new Marker());
    try
    {
      // stage initialization
      Backdrop backdrop = new Backdrop();
      VisualizationView stageView = backdrop.getView();

      // test for adding a sunbelt sprite
      SunbeltSprite sunbeltSprite = new SunbeltSprite();
      HashMap<String, BufferedImage> testMap = sunbeltSprite.getTeamImgMap();
      sunbeltSprite.addKeyTime(0, 310, 255, sunbeltSprite.getContent());

      sunbeltSprite.addKeyTime(2, 311, 255, null);

      //      sunbeltSprite.addKeyTime(2, 314, 234, null);
      //      sunbeltSprite.addKeyTime(3, 401, 231, null);
      backdrop.add(sunbeltSprite);

      stageView.setBounds(0, 0, WIDTH, HEIGHT);

      contentPane.add(stageView);
      backdrop.start();

      //
      //
      //
      //
      //      SunbeltSprite sunbeltSprite = new SunbeltSprite();
      //      sunbeltSprite.lodaImgToSprite("georgia_south" + SunbeltSprite.PNG);
      //
      //
      //      contentFactory = new ContentFactory();
      //      sunbeltSprite.addKeyTime(1, new Point(800, 400),  0.0, 1.0, contentFactory.createContent(sunbeltSprite.getImage()));
      //      sunbeltSprite.setVisible(true);
      //
      //
      //      backdrop.add(sunbeltSprite);
      //
      //      backdrop.start();
      //      VisualizationView backDropVis = backdrop.getView();
      //      contentPane.add(backDropVis);
      //      sunbeltSprite.setEndState(TweeningSprite.REMOVE);

    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }

  }

  /**
   *
   */
  public static void main(String[] args)
  {
    FloppyDriver floppyDriver = new FloppyDriver(args);
    invokeInEventDispatchThread(floppyDriver);
  }
}
