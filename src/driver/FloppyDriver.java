package driver;

import app.JApplication;
import gameplay.Backdrop;

import javax.swing.*;
import java.io.IOException;

public class FloppyDriver extends JApplication
{
  public static final int WIDTH = 900;
  public static final int HEIGHT = 500;
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
    try
    {
      Backdrop backdrop = new Backdrop();
      JComponent component = backdrop.getView();
      component.setBounds(0, 0, WIDTH, HEIGHT);
      contentPane.add(component);


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
