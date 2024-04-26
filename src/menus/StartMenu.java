package menus;

import io.ResourceFinder;
import resources.Marker;
import visual.Visualization;
import visual.VisualizationRenderer;
import visual.VisualizationView;
import visual.dynamic.described.Stage;
import visual.statik.TransformableContent;

import java.awt.AWTKeyStroke;
import javax.swing.*;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Liam J Herkins
 * @version 4/22/24
 */
public class StartMenu extends Stage implements KeyListener
{

  private static final Color ROYAL_PURPLE = new Color(0x7851a9);
  private static final Color DUKES_GOLD = new Color(0xFFDF00);
  private static final Color BACKGROUND_COLOR = Color.black;
  private TransformableContent startLabel;
  private TransformableContent optionsLabel;
  private TransformableContent exitLabel;
  private ResourceFinder finder;

  /**
   *
   */
  public StartMenu()
  {
    super(100);
    finder = ResourceFinder.createInstance(new Marker());

  }

  /**
   * Invoked when a key has been typed. See the class description for {@link KeyEvent} for a
   * definition of a key typed event.
   *
   * @param e
   *     the event to be processed
   */
  @Override
  public void keyTyped(KeyEvent e)
  {
  // not needed
  }

  /**
   * Invoked when a key has been pressed. See the class description for {@link KeyEvent} for a
   * definition of a key pressed event.
   *
   * @param e
   *     the event to be processed
   */
  @Override
  public void keyPressed(KeyEvent e)
  {
    switch (e.getKeyCode())
    {
      case KeyEvent.VK_ENTER:
        handleStart();
        break;
      case KeyEvent.VK_ESCAPE:
        handleExit();
        break;
      case KeyEvent.VK_WINDOWS:
        handleOptions();
        break;
      default:
        break;
    }
  }

  /**
   * Invoked when a key has been released. See the class description for {@link KeyEvent} for a
   * definition of a key released event.
   *
   * @param e
   *     the event to be processed
   */
  @Override
  public void keyReleased(KeyEvent e)
  {
    // not needed
  }

  /**
   *
   */
  public void handleStart()
  {

  }

  /**
   *
   */
  public void handleOptions()
  {

  }

  public void handleExit()
  {

  }

  public void init()
  {

  }
}
