import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
  private static Pattern partOne = Pattern.compile("mul\\((\\d+),(\\d+)\\)"); // matches the 2 numbers if they are in
                                                                              // the mul(num,num) format
  private static Pattern partTwo = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)"); // matches either a
                                                                                                   // do() a don't() or
                                                                                                   // partOne

  public static int sumValidMultiplications(String text, Pattern pattern) {
    int totalSum = 0; // Counter
    boolean isEnabled = true; // Will change based on what the most recent do or don't is when it comes time
                              // to see if a mul is correct

    Matcher matcher = pattern.matcher(text);

    while (matcher.find()) {
      String match = matcher.group();
      if (match.equals("do()")) {
        isEnabled = true;
      } else if (match.equals("don't()")) {
        isEnabled = false;
      } else {
        if (isEnabled) { // if the last thing before a mul was dont this gets skipped
          int x = Integer.parseInt(matcher.group(1));
          int y = Integer.parseInt(matcher.group(2));
          totalSum += x * y;
        }
      }
    }

    return totalSum;
  }

  private static void solver() {
    // Getting the entire file onto a string to pass onto the solver
    Path filename = Path.of("inputs.txt");
    try {
      String text = Files.readString(filename);
      System.out.println("Part One Solution: " + sumValidMultiplications(text, partOne));
      System.out.println("Part Two Solution: " + sumValidMultiplications(text, partTwo));
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }

  }

  public static void main(String[] args) {
    solver();
  }
}
