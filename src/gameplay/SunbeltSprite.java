package gameplay;

import io.ResourceFinder;
import resources.Marker;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.SampledSprite;
import visual.statik.sampled.Content;
import visual.statik.sampled.ContentFactory;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class SunbeltSprite extends SampledSprite
{
  public static final String[] TEAM_NAMES = {"app_state", "ark_state", "cajuns", "coastal",
      "georgia_south", "georgia_st", "marshall", "odu", "south_bama", "south_miss", "texas_state",
      "troy", "ulm"};
  public static final String PNG = ".png";

  private BufferedImage currTeam;
  private ResourceFinder finder;
  private String teamName;
  private ContentFactory contentFactory;
  private visual.statik.sampled.Content logoContent;

  /**
   *
   */
  public SunbeltSprite() throws IOException
  {
    super();
    finder = ResourceFinder.createInstance(new Marker());
    contentFactory = new ContentFactory();
    setLocation(100, 100);
    setEndState(REMAIN);

  }

  /**
   * @param fileName
   * @throws IOException
   */
  public void loadImgToSprite(final String fileName) throws IOException
  {
    teamName = fileName;
    InputStream ioStream = finder.findInputStream(fileName);
    currTeam = ImageIO.read(ioStream);
    logoContent = contentFactory.createContent(currTeam);

  }

  /**
   *
   */
  public void reset()
  {
    this.setLocation(0, 0);
  }

  /**
   * @return
   */
  public BufferedImage getImage()
  {
    return currTeam;
  }

  /**
   * Add a key time
   *
   * @param time
   *     The key time
   * @param x
   *     The x position
   * @param y
   *     The y position
   * @param content
   *     The Content to use
   */
  public void addKeyTime(int time, int x, int y, visual.statik.sampled.Content content)
  {
    addKeyTime(time * 1000, new Point2D.Double((double) x, (double) y), null, 1.0,
        content);
  }

  /**
   *
   * @return
   */
  public Content getContent()
  {
    return logoContent;
  }

}
