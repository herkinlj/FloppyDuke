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
  private BufferedImage backdropImg;
  private visual.statik.sampled.Content wilsonContent;

  /**
   *
   * @throws IOException
   */
  public Backdrop() throws IOException
  {
    super(100);
    finder = ResourceFinder.createInstance(new Marker());
    InputStream isStreamBackdrop;
    isStreamBackdrop = finder.findInputStream("blue_ridge_backdrop.jpg");
    backdropImg = ImageIO.read(isStreamBackdrop);
    wilsonContent = new Content(backdropImg, 0, 0);
    wilsonContent.setScale(.5);
    add(wilsonContent);
  }

  public Content getWilsonContent()
  {
    return wilsonContent;
  }
}


