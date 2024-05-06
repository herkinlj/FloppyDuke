package tak34;

import io.ResourceFinder;
import resources.Marker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;

public class StartMenu extends JPanel implements KeyListener
{
  private MainApp switcher;
  private final int PANEL_WIDTH = 900;
  private final int PANEL_HEIGHT = 580;
  private Font minecraft;
  private boolean active;
  private String[] teamNames = {"app_state", "ark_state", "cajuns", "coastal", "georgia_south",
      "georgia_st", "marshall", "odu", "south_bama", "south_miss", "texas_state", "troy", "ulm"};

  private ResourceFinder resourceFinder;
  private Image bridgeforth;

  public StartMenu() throws IOException, FontFormatException
  {
    resourceFinder = ResourceFinder.createInstance(Marker.class);
    setPanelSize();
    setFocusable(true);
    addKeyListener(this);
    minecraft = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\The Herks\\IdeaProjects\\FloppyDuke\\src\\resources\\Minecraft.ttf"));
    minecraft = minecraft.deriveFont(32f);
    InputStream ioStream = resourceFinder.findInputStream("bridgeforth.jpg");
    bridgeforth = ImageIO.read(ioStream);

  }
  public void setPanelSize() {
    Dimension dimension = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    setMinimumSize(dimension);
    setMinimumSize(dimension);
    setPreferredSize(dimension);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.drawImage(bridgeforth.getScaledInstance(PANEL_WIDTH, PANEL_HEIGHT, Image.SCALE_DEFAULT), 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
    g2.setFont(minecraft);
    g2.setColor(new Color(0xA335DE));
    String line = "Our sworn enemies in the SBC are on the quad!\navoid any contact with your foes all while\ncontrolling the floating head of...........";
    int x = 60;
    int y = 75;
    for (String word : line.split("\n"))
    {

      g2.drawString(word, x, y += g.getFontMetrics().getHeight());

    }
    g2.setColor(Color.white);
    g2.drawString("\tTHE BERNSTEIN", x + 200, y += g.getFontMetrics().getHeight() + 50);
    g2.drawString("Press enter to begin.", 250, 350);

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
  public void setSwitcher(MainApp switcher) {
    this.switcher = switcher;
  }
  private void setActive(boolean status)
  {
    active = status;
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
    if (e.getKeyCode() == KeyEvent.VK_ENTER && switcher != null)
    {
      switcher.startGame();
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

  public boolean getActive()
  {
    return active;
  }
}
