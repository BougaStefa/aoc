import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

  private static ArrayList<String> getListOfSequences(String text) {
    ArrayList<String> seqList = new ArrayList<>();
    Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      String item = matcher.group();
      seqList.add(item);
    }
    return seqList;
  }

  private static int seqToMath(String seq) {
    String[] splitSeq = seq.split(",");
    return Integer.parseInt(splitSeq[0].substring(4))
        * Integer.parseInt(splitSeq[1].substring(0, splitSeq[1].length() - 1));
  }

  private static int sumOfResults(ArrayList<String> list) {
    int total = 0;
    for (String seq : list) {
      total += seqToMath(seq);
    }
    return total;
  }

  private static void partOneSolver(Path filename) {
    try {
      String content = Files.readString(filename);
      ArrayList<String> seqList = getListOfSequences(content);
      System.out.println("The total is: " + sumOfResults(seqList));
    } catch (IOException e) {
      System.out.println("Could not read file: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Path filename = Path.of("inputs.txt");
    partOneSolver(filename);
  }
}
