public class Day6Part2 {

  private static void sketchGrid(String[][] grid, int[] coordinates) {
    String direction = "up";
    int currentRow = coordinates[0];
    int currentColumn = coordinates[1];
    boolean keepGoing = true;
    while (keepGoing) {
      switch (direction) {
        case "up":
          for (int row = currentRow; row >= 0; row--) {
            if (grid[row][currentColumn].equals("#")) {
              direction = "right";
              currentRow = row + 1;
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

}
