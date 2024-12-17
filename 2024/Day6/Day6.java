import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {
  private static final String input = "input.txt";
  private static final String test = "test.txt";
  private static String[][] mapGrid = getInitialGrid(test);

  // Get initial grid
  private static String[][] getInitialGrid(String filename) {
    List<String[]> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] elements = line.split("");
        lines.add(elements);
      }
    } catch (IOException e) {
      System.out.println("Could not read from file: " + e.getMessage());
    }
    int numRows = lines.size();
    int numCols = lines.get(0).length;
    String[][] grid = new String[numRows][numCols];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numCols; j++) {
        grid[i][j] = lines.get(i)[j];
      }
    }
    return grid;
  }

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
            } else {
              grid[row][currentColumn] = "X";
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
            } else {
              grid[currentRow][column] = "X";
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
            } else {
              grid[row][currentColumn] = "X";
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
            } else {
              grid[currentRow][column] = "X";
            }
            if (column == 0) {
              keepGoing = false;
            }
          }
      }
    }
  }

  private static int[] getInitialCoordinates(String[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j].equals("^")) {
          int[] coords = { i, j };
          return coords;
        }
      }
    }
    return null;
  }

  private static int getDistinctPositions(String[][] grid) {
    int counter = 0;
    for (String[] row : grid) {
      for (String element : row) {
        if (element.equals("X")) {
          counter++;
        }
      }
    }
    return counter;
  }

  private static void partOneSolver() {
    int[] coordinates = getInitialCoordinates(mapGrid);
    sketchGrid(mapGrid, coordinates);
    int totalDistinctPositions = getDistinctPositions(mapGrid);
    System.out.println("The total number of distinct positions is: " + totalDistinctPositions);
  }

  public static void main(String[] args) {
    partOneSolver();
  }

}
