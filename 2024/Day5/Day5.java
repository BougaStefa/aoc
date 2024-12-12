import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
  private static final String input = "inputs.txt";
  private static final String test = "test.txt";
  private static final List<int[]> pages = getPages(input);
  private static final List<int[]> rules = getRules(input);

  private static List<int[]> getRules(String filename) {
    List<int[]> xy = new ArrayList<>();
    Pattern xyPattern = Pattern.compile("(\\d+)\\|(\\d+)");
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        Matcher match = xyPattern.matcher(line);
        if (match.matches()) {
          int x = Integer.parseInt(match.group(1));
          int y = Integer.parseInt(match.group(2));
          xy.add(new int[] { x, y });
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
    return xy;
  }

  private static List<int[]> getPages(String filename) {
    List<int[]> pages = new ArrayList<>();
    Pattern pagePattern = Pattern.compile("^\\d+(,\\d+)+$");
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        Matcher match = pagePattern.matcher(line);
        if (match.matches()) {
          String[] numsStr = line.split(",");
          int[] numInt = Arrays.stream(numsStr).mapToInt(Integer::parseInt).toArray();
          pages.add(numInt);
        }
      }
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
    return pages;
  }

  private static Set<Integer> getMustBeBefore(int page, List<int[]> rules) {
    Set<Integer> mustBeBefore = new HashSet<>();
    for (int[] coordinates : rules) {
      if (coordinates[0] == page) {
        mustBeBefore.add(coordinates[1]);
      }
    }
    return mustBeBefore;
  }

  private static Set<Integer> getMustBeAfter(int page, List<int[]> rules) {
    Set<Integer> mustBeBefore = new HashSet<>();
    for (int[] coordinates : rules) {
      if (coordinates[1] == page) {
        mustBeBefore.add(coordinates[0]);
      }
    }
    return mustBeBefore;
  }

  private static boolean isSafeBefore(Set<Integer> rules, int[] pages, int index) {
    for (int i = 0; i < index; i++) {
      if (rules.contains(pages[i])) {
        return false;
      }
    }
    return true;
  }

  private static boolean isSafeAfter(Set<Integer> rules, int[] pages, int index) {
    for (int i = index + 1; i < pages.length; i++) {
      if (rules.contains(pages[i])) {
        return false;
      }
    }
    return true;
  }

  private static void getSumOfValids() {
    int counter = 0;
    for (int[] page : pages) {
      boolean looksGood = true;
      for (int i = 0; i < page.length; i++) {
        boolean safeBeforeResult = isSafeBefore(getMustBeBefore(page[i], rules), page, i);
        boolean safeAfterResult = isSafeAfter(getMustBeAfter(page[i], rules), page, i);

        if (!safeBeforeResult || !safeAfterResult) {
          looksGood = false;
          break;
        }
      }
      if (looksGood) {
        counter += page[Math.round(page.length / 2)];
      }

    }
    System.out.println("\nSum for part one is: " + counter);
  }

  private static List<int[]> getInvalidPages() {
    List<int[]> invalids = new ArrayList<>();
    for (int[] page : pages) {
      for (int i = 0; i < page.length; i++) {
        boolean safeBeforeResult = isSafeBefore(getMustBeBefore(page[i], rules), page, i);
        boolean safeAfterResult = isSafeAfter(getMustBeAfter(page[i], rules), page, i);
        if (!safeBeforeResult || !safeAfterResult) {
          invalids.add(page);
          break;
        }
      }
    }
    return invalids;
  }

  private static void getSumOfInvalids(List<int[]> invalids) {
    int counter = 0;
    for (int[] page : invalids) {
      for (int i = 0; i < page.length; i++) {
        Set<Integer> mustBeBefore = getMustBeBefore(page[i], rules);
        for (int j = i + 1; j < page.length; j++) {
          if (mustBeBefore.contains(page[j])) {
            int temp = page[i];
            page[i] = page[j];
            page[j] = temp;
            i--;
            break;
          }
        }
      }
      counter += page[Math.round(page.length / 2)];
    }
    System.out.println("Total sum of middle elements: " + counter);
  }

  public static void main(String[] args) {
    getSumOfValids();
    getSumOfInvalids(getInvalidPages());
  }
}
