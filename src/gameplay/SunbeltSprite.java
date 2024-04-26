package gameplay;

import io.ResourceFinder;
import resources.Marker;
import visual.dynamic.described.RuleBasedSprite;
import visual.dynamic.described.SampledSprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SunbeltSprite extends SampledSprite
{
  private BufferedImage currTeam;
  private ResourceFinder finder;
  private
  /**
   *
   */
  public SunbeltSprite(String teamFileName) throws IOException
  {
    finder = ResourceFinder.createInstance(new Marker());
    InputStream ioStream = finder.findInputStream(teamFileName);
    currTeam = ImageIO.read(ioStream);

  }
}
