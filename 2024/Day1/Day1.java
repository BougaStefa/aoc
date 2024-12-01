import java.util.ArrayList;
import java.util.stream.IntStream;
import java.io.*;
import java.util.*;

public class Day1 {
  public static void main(String[] args) {
    String filepath = "inputs.txt";
    List<Integer> column1 = new ArrayList<>();
    List<Integer> column2 = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
      String line;
      while ((line = br.readLine()) != null) {
        column1.add(Integer.parseInt(line.split("   ")[0]));
        column2.add(Integer.parseInt(line.split("   ")[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    Collections.sort(column1);
    Collections.sort(column2);

    if (column1.size() != column2.size()) {
      System.out.println("Woops, you've done goofed.");
      return;
    }
    System.out.println("Total distance between the lists is: " + IntStream.range(0, column1.size())
        .mapToObj(i -> Math.abs(column1.get(i) - column2.get(i))).mapToInt(Integer::intValue).sum());

    // PART TWO
    System.out.println("The similarity score is: " + column1.stream().mapToInt(num -> {
      long count = column2.stream().filter(x -> x.equals(num)).count();
      return (int) (count * num);
    }).sum());
  }
}
