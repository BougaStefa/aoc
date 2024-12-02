import java.io.BufferedReader;
import java.util.stream.Collectors;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day2 {
  private static final String input = "inputs.txt";
  private static final String test = "testputs.txt";

  // Gets a line and converts it to a list of integers split at whitespaces
  private static ArrayList<Integer> convertStringToNumberList(String nums) {
    return Arrays.stream(nums.trim().split("\\s+")).map(Integer::parseInt)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  private static boolean isValidSequence(ArrayList<Integer> numbers) {
    boolean ascending = numbers.get(1) > numbers.get(0);
    for (int i = 0; i < numbers.size() - 1; i++) {
      // Calculating raw and abs diffs for later checks
      int diff = numbers.get(i + 1) - numbers.get(i);
      int absDiff = Math.abs(diff);
      // List must be moving by 1 to 3, either up or down
      // If the list is ascending next minus current index must be positive
      // If the list is descending next minus current index must be negative
      if (absDiff > 3 || absDiff < 1 || (ascending && diff < 0) || (!ascending && diff > 0)) {
        return false;
      }
    }
    return true;
  }

  private static boolean problemDampener(ArrayList<Integer> nums) {
    // In case no dampener is needed
    if (isValidSequence(nums))
      return true;
    for (int i = 0; i < nums.size(); i++) {
      // Creating a test list based of the original and removing current index
      ArrayList<Integer> testList = new ArrayList<>(nums);
      testList.remove(i);
      // If the new list is good to go great
      // If not we will check every iteration of the list minus one number
      if (isValidSequence(testList))
        return true;
    }
    return false;
  }

  private static void partOneSolver(String filename) {
    int totalSafe = 0; // Counter
    // Reading each line, every line that's valid increases counter by one.
    // After all the lines are read print the final counter
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        ArrayList<Integer> nums = convertStringToNumberList(line);
        if (isValidSequence(nums)) {
          totalSafe++;
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
    System.out.println("Total safe reports: " + totalSafe);
  }

  private static void partTwoSolver(String filename) {
    int totalSafe = 0;
    // Exact same function as part one but we increment based on different criteria
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        ArrayList<Integer> nums = convertStringToNumberList(line);
        if (problemDampener(nums)) {
          totalSafe++;
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
    System.out.println("Total safe reports: " + totalSafe);
  }

  public static void main(String[] args) {
    partOneSolver(input);
    partTwoSolver(input);
  }
}
