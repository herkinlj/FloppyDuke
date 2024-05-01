package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import take3.Game;
import take3.GamePanel;
import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

  private GamePanel gamePanel;

  public KeyboardInputs(GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
        gamePanel.setMoving(false);
        break;
      case KeyEvent.VK_A:
        gamePanel.setMoving(false);
        break;
      case KeyEvent.VK_S:
        gamePanel.setMoving(false);
        break;
      case KeyEvent.VK_D:
        gamePanel.setMoving(false);
        break;
    }

  }

  /**
   *
   * @param e the event to be processed
   */
  @Override
  public void keyPressed(KeyEvent e) {

    switch (e.getKeyCode()) {
      case KeyEvent.VK_W:
        gamePanel.setPlayerDirection(UP);
        break;
      case KeyEvent.VK_A:
        gamePanel.setPlayerDirection(LEFT);
        break;
      case KeyEvent.VK_S:
        gamePanel.setPlayerDirection(DOWN);
        break;
      case KeyEvent.VK_D:
        gamePanel.setPlayerDirection(RIGHT);
        break;
    }

  }

}
