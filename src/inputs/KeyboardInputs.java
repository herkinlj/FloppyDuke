package inputs;

import take3.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener
{
  private GamePanel gamePanel;

  public KeyboardInputs(GamePanel gp)
  {
    this.gamePanel = gp;
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
      case KeyEvent.VK_A:
        gamePanel.moveDx(-5);
        break;
      case KeyEvent.VK_W:
        gamePanel.moveDy(-5);
        break;
      case KeyEvent.VK_S:
        gamePanel.moveDy(5);
        break;
      case KeyEvent.VK_D:
        gamePanel.moveDx(5);
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

  }
}
