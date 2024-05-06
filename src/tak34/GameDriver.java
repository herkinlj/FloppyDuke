package tak34;

import java.awt.*;
import java.io.IOException;

public class GameDriver
{
  private Game game;
  private GameWindow gameWindow;
  private StartMenu menu;


  public GameDriver() throws IOException, FontFormatException
  {
    init();
  }
  public void init() throws IOException, FontFormatException
  {
    game = new Game();
    gameWindow = new GameWindow(game);
    game.requestFocus();
  }


}
