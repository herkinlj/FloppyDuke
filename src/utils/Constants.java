package utils;

public class Constants
{
  public static class Directions
  {
    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;


  }
  public static class PlayerConstants {

    public static final int IDLE = 0;
    public static final int RUNNING = 0;
    public static final int HEADBUTT = 3;
    public static final int JUMPING = 0;
    public static final int DEAD = 1;

    public static int GetSpriteAmount(int player_action)
    {
      return switch (player_action)
      {
        case RUNNING -> 0;
        case HEADBUTT -> 3;
        case DEAD -> 1;
        default -> -1;
      };
    }
  }
}
