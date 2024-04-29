package utils;

public class Constants
{
  public static class PlayerConstants {
    public static final int RUNNING = 0;
    public static final int HEADBUTT = 1;
    public static final int JUMPING = 2;
    public static final int DEAD = 3;

    public static int GetSpriteAmount(int player_action)
    {
      return 1;
    }
  }
}
