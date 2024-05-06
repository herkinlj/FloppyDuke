package tak34;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NumberShapeGenerator {

  // Define the segments for each digit
  private static final boolean[][] SEGMENTS = {
      {true, true, true, false, true, true, true},   // 0
      {false, false, true, false, false, true, false}, // 1
      {true, false, true, true, true, false, true},  // 2
      {true, false, true, true, false, true, true},  // 3
      {false, true, true, true, false, true, false}, // 4
      {true, true, false, true, false, true, true},  // 5
      {true, true, false, true, true, true, true},   // 6
      {true, false, true, false, false, true, false},// 7
      {true, true, true, true, true, true, true},   // 8
      {true, true, true, true, false, true, true}    // 9
  };

  public static List<Shape> generateDigitShapes(int digit, int x, int y, int width, int height) {
    if (digit < 0 || digit > 9) {
      throw new IllegalArgumentException("Digit must be between 0 and 9");
    }

    List<Shape> shapes = new ArrayList<>();

    // Get the segments for the specified digit
    boolean[] segments = SEGMENTS[digit];

    // Define the positions and dimensions of each segment
    int[][] segmentPositions = {
        {x, y},                         // Top
        {x + width - 2, y + 2},         // Top-right
        {x + width - 2, y + height / 2},// Middle-right
        {x, y + height - 2},            // Bottom
        {x, y + height / 2},            // Middle-left
        {x + width - 2, y},             // Top-left
        {x, y + height / 2},            // Middle
    };

    // Generate shapes for each segment
    for (int i = 0; i < 7; i++) {
      if (segments[i]) {
        int[] pos = segmentPositions[i];
        shapes.add(new Rectangle(pos[0], pos[1], width - 2, height / 2 - 2));
      }
    }

    return shapes;
  }

  public static void main(String[] args) {
    int digit = 5;
    int x = 50;
    int y = 50;
    int width = 50;
    int height = 100;

    List<Shape> digitShapes = generateDigitShapes(digit, x, y, width, height);

    // Example usage: draw the digit shapes
//  //  Graphics2D g2d = (Graphics2D) g;
//    g2d.setColor(Color.BLACK);
//    for (Shape shape : digitShapes) {
//      g2d.fill(shape);
//    }
  }
}

