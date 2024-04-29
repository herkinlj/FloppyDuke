package take3;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel
{

  private MouseInputs mouseInputs;
  private int dX = 100, dY = 100;
  public GamePanel()
  {
    mouseInputs = new MouseInputs(this);
    addKeyListener(new KeyboardInputs(this));
    addMouseListener(mouseInputs);
    addMouseMotionListener(mouseInputs);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.fillRect(dX, dY, 200, 50);
  }

  /**
   *
   */
  public void moveDx(int val)
  {
    dX += val;
    repaint();
  }

  /**
   *
   * @param val
   */
  public void moveDy(int val)
  {
    dY += val;
    repaint();
  }
  public void setRectPos(int x, int y)
  {
    this.dX = x;
    this.dY = y;
    repaint();
  }


}
