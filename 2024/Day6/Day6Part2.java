import java.util.ArrayList;
import java.util.List;

public class Day6Part2 {

  private static void sketchGrid(String[][] grid, int[] coordinates) {
    String direction = "up";
    int currentRow = coordinates[0];
    int currentColumn = coordinates[1];
    boolean keepGoing = true;
    List<Integer> upCoordinates = new ArrayList<>();
    boolean isLoop = false;
    while (keepGoing) {
      switch (direction) {
        case "up":
          for (int row = currentRow; row >= 0; row--) {
            if (grid[row][currentColumn].equals("#")) {
              if (upCoordinates.get(0) == currentRow && upCoordinates.get(1) == currentColumn) {
                isLoop = true;
                keepGoing = false;
              }
              direction = "right";
              currentRow = row + 1;
              upCoordinates.add(currentRow);
              upCoordinates.add(currentColumn);
              break;
            }
            if (row == 0) {
              keepGoing = false;
            }
          }
          break;
        case "right":
          for (int column = currentColumn; column < grid[currentRow].length; column++) {
            if (grid[currentRow][column].equals("#")) {
              direction = "down";
              currentColumn = column - 1;
              break;
            }
            if (column == grid[currentRow].length - 1) {
              keepGoing = false;
            }
          }
          break;
        case "down":
          for (int row = currentRow; row < grid.length; row++) {
            if (grid[row][currentColumn].equals("#")) {
              direction = "left";
              currentRow = row - 1;
              break;
            }
            if (row == grid.length - 1) {
              keepGoing = false;
            }
          }
          break;
        case "left":
          for (int column = currentColumn; column >= 0; column--) {
            if (grid[currentRow][column].equals("#")) {
              direction = "up";
              currentColumn = column + 1;
              break;
            }
            if (column == 0) {
              keepGoing = false;
            }
          }
      }
    }
  }

  private static void findTotalLoops(String[][] array, int[] coordinates) {
    int currentRow = coordinates[0];
    int currentColumn = coordinates[1];
    int j = 0;
    int totalPositions = 0;
    for (String[] line : array) {
      for (int i = 0; i < line.length; i++) {
        array[j][i] = "#";
        boolean keepGoing = true;
        String direction = "up";
        while (keepGoing) {
          switch (direction) {
            case "up":
              for (int row = currentRow; row >= 0; row--) {
                if (array[row][currentColumn].equals("#")) {
                  direction = "right";
                  currentRow = row + 1;
                  break;
                }
                if (row == 0) {
                  keepGoing = false;
                }
              }
            case "right":
              for (int column = currentColumn; column < array[currentRow].length; column++) {
                if (array[currentRow][column].equals("#")) {
                  direction = "down";
                  currentColumn = column - 1;
                  break;
                }
              }
              break;
            case "down":
              for (int row = currentRow; row < array.length; row++) {
                if (array[row][currentColumn].equals("#")) {
                  direction = "left";
                  currentRow = row - 1;
                  break;
                }
                if (row == array.length - 1) {
                  keepGoing = false;
                }
              }
              break;
            case "left":
              for (int column = currentColumn; column >= 0; column--) {
                if (array[currentRow][column].equals("#")) {
                  direction = "up";
                  currentColumn = column + 1;
                  break;
                }
                if (column == 0) {
                  keepGoing = false;
                }
              }
          }
        }
      }
      j++;
    }
  }
}
