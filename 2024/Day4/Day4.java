import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4 {
  private static final String input = "inputs.txt";
  private static final String test = "test.txt";

  private static ArrayList<String> linesToList(String filename) {
    ArrayList<String> list = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        list.add(line);
      }
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
    return list;
  }

  private static void partOneSolver() {
    int counter = 0;
    ArrayList<String> list = linesToList(input);
    for (int i = 0; i < list.size(); i++) {
      String line = list.get(i);
      for (int j = 0; j < line.length(); j++) {
        String character = String.valueOf(line.charAt(j));
        if (character.equals("X")) {
          counter += isLeftRightXmas(line, j) ? 1 : 0;
          counter += isRightLeftXmas(line, j) ? 1 : 0;
          counter += isDownXmas(list, i, j) ? 1 : 0;
          counter += isUpXmas(list, i, j) ? 1 : 0;
          counter += isDiagUpRight(list, i, j) ? 1 : 0;
          counter += isDiagUpLeft(list, i, j) ? 1 : 0;
          counter += isDiagDownRight(list, i, j) ? 1 : 0;
          counter += isDiagDownLeft(list, i, j) ? 1 : 0;
        }
      }
    }
    System.out.println("Total is: " + counter);
  }

  private static boolean isLeftRightXmas(String line, int lineIndex) {
    if (line.length() - lineIndex < 4) {
      return false;
    }
    return line.substring(lineIndex + 1, lineIndex + 4).equals("MAS");
  }

  private static boolean isRightLeftXmas(String line, int lineIndex) {
    if (lineIndex < 3) {
      return false;
    }
    return line.substring(lineIndex - 3, lineIndex).equals("SAM");
  }

  private static boolean isDownXmas(ArrayList<String> list, int index, int lineIndex) {
    if (list.size() - index < 4) {
      return false;
    }
    String couldBeRight = list.get(index).substring(lineIndex, lineIndex + 1)
        + list.get(index + 1).substring(lineIndex, lineIndex + 1)
        + list.get(index + 2).substring(lineIndex, lineIndex + 1)
        + list.get(index + 3).substring(lineIndex, lineIndex + 1);
    return couldBeRight.equals("XMAS");
  }

  private static boolean isUpXmas(ArrayList<String> list, int index, int lineIndex) {
    if (index < 3) {
      return false;
    }
    String couldBeRight = list.get(index).substring(lineIndex, lineIndex + 1)
        + list.get(index - 1).substring(lineIndex, lineIndex + 1)
        + list.get(index - 2).substring(lineIndex, lineIndex + 1)
        + list.get(index - 3).substring(lineIndex, lineIndex + 1);
    return couldBeRight.equals("XMAS");
  }

  private static boolean isDiagUpRight(ArrayList<String> list, int index, int lineIndex) {
    // The spot X was found out must have at least 3 rows up and be 3 away from the
    // right edge for the "MAS" to have enough room to exist
    if (index < 3 || list.get(index).length() - lineIndex < 4) {
      return false;
    }
    String potentialM = list.get(index - 1).substring(lineIndex + 1, lineIndex + 2);
    String potentialA = list.get(index - 2).substring(lineIndex + 2, lineIndex + 3);
    String potentialS = list.get(index - 3).substring(lineIndex + 3, lineIndex + 4);
    return potentialM.equals("M") && potentialA.equals("A") && potentialS.equals("S");
  }

  private static boolean isDiagUpLeft(ArrayList<String> list, int index, int lineIndex) {
    if (index < 3 || lineIndex < 3) {
      return false;
    }
    String potentialM = list.get(index - 1).substring(lineIndex - 1, lineIndex);
    String potentialA = list.get(index - 2).substring(lineIndex - 2, lineIndex - 1);
    String potentialS = list.get(index - 3).substring(lineIndex - 3, lineIndex - 2);
    return potentialM.equals("M") && potentialA.equals("A") && potentialS.equals("S");
  }

  private static boolean isDiagDownRight(ArrayList<String> list, int index, int lineIndex) {
    if (list.size() - index < 4 || list.get(index).length() - lineIndex < 4) {
      return false;
    }
    String potentialM = list.get(index + 1).substring(lineIndex + 1, lineIndex + 2);
    String potentialA = list.get(index + 2).substring(lineIndex + 2, lineIndex + 3);
    String potentialS = list.get(index + 3).substring(lineIndex + 3, lineIndex + 4);
    return potentialM.equals("M") && potentialA.equals("A") && potentialS.equals("S");
  }

  private static boolean isDiagDownLeft(ArrayList<String> list, int index, int lineIndex) {
    if (list.size() - index < 4 || lineIndex < 3) {
      return false;
    }
    String potentialM = list.get(index + 1).substring(lineIndex - 1, lineIndex);
    String potentialA = list.get(index + 2).substring(lineIndex - 2, lineIndex - 1);
    String potentialS = list.get(index + 3).substring(lineIndex - 3, lineIndex - 2);
    return potentialM.equals("M") && potentialA.equals("A") && potentialS.equals("S");
  }

  // PART 2
  private static boolean isDiagRight(ArrayList<String> list, int index, int lineIndex) {
    // Got to have at least room for 1 thing above, below, right and left
    if (index < 1 || list.size() - index < 1 || lineIndex < 1 || list.get(index).length() - lineIndex < 1) {
      return false;
    }
    String topLeft = list.get(index - 1).substring(lineIndex - 1, lineIndex);
    String bottomRight = list.get(index + 1).substring(lineIndex + 1, lineIndex + 2);
    return (topLeft.equals("M") && bottomRight.equals("S")) || (topLeft.equals("S") && bottomRight.equals("M"));
  }

  private static boolean isDiagLeft(ArrayList<String> list, int index, int lineIndex) {
    // Got to have at least room for 1 thing above, below, right and left
    if (index == 0 || index == list.size() - 1 || lineIndex == 0 || lineIndex == list.get(index).length() - 1) {
      return false;
    }
    String topRight = list.get(index - 1).substring(lineIndex + 1, lineIndex + 2);
    String bottomLeft = list.get(index + 1).substring(lineIndex - 1, lineIndex);
    return (topRight.equals("M") && bottomLeft.equals("S")) || (topRight.equals("S") && bottomLeft.equals("M"));
  }

  private static void partTwoSolver() {
    int counter = 0;
    ArrayList<String> list = linesToList(input);
    for (int i = 0; i < list.size(); i++) {
      String line = list.get(i);
      for (int j = 0; j < line.length(); j++) {
        String character = String.valueOf(line.charAt(j));
        if (character.equals("A")) {
          counter += (isDiagLeft(list, i, j) && isDiagRight(list, i, j)) ? 1 : 0;
        }
      }
    }
    System.out.println("Total is: " + counter);
  }

  public static void main(String[] args) {
    partOneSolver();
    partTwoSolver();
  }
}
