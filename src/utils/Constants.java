package utils;

public class Constants
{
  public static class PlayerConstants {
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
