package gameplay;

import io.ResourceFinder;
import resources.Marker;
import visual.dynamic.described.Stage;
import visual.statik.sampled.BufferedImageOpFactory;
import visual.statik.sampled.Content;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;
import java.io.InputStream;

public class Backdrop extends Stage
{

  private ResourceFinder finder;
  private BufferedImage wilsonHallImg;
  private visual.statik.sampled.Content wilsonContent;

  /**
   *
   * @throws IOException
   */
  public Backdrop() throws IOException
  {
    super(100);
    finder = ResourceFinder.createInstance(new Marker());
    InputStream iStreamWilson;
    iStreamWilson = finder.findInputStream("wilson_hall.jpg");
    wilsonHallImg = ImageIO.read(iStreamWilson);
    wilsonContent = new Content(wilsonHallImg, 0, 0);
    add(wilsonContent);
  }

}


