
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
  public static void main(String[] args) {
    String source = "src/source.txt";
    List<String> linesOfSource = extractLinesFromExamples(source);
    List<int[]> presents = new ArrayList<>();
    for (String line : linesOfSource) {
      presents.add(extractNumbersFromLines(line));
    }
    System.out.println("paper " +feetOfWrappingPaper(presents));

    System.out.println("ribbon " + feetOfRibbon(presents));
  }

  private static int feetOfRibbon(List<int[]> boxes) {
    int ribbon = 0;
    for (int[] box : boxes) {
      ribbon += ribbonPerBox(box);
    }
    return ribbon;
  }

  public static int ribbonPerBox(int[] box) {
    int l = box[0];
    int w = box[1];
    int h = box[2];
    // find the 2 smallest number in one box
    int smallest = Math.min(l, Math.min(w, h));
    int secondSmallest;
    if (smallest == l) {
      secondSmallest = Math.min(w, h);
    } else if (smallest == w) {
      secondSmallest = Math.min(l, h);
    } else {
      secondSmallest = Math.min(l, w);
    }
    //duplicate both and add them together
    int perimeter = 2 * (smallest + secondSmallest);
    // add the multiplication of the 3 numbers
    int volume = l * w * h;

    return perimeter + volume;
  }

  public static int feetOfWrappingPaper(List<int[]> boxes) {
    int paper = 0;
    for (int[] box : boxes) {
      paper += paperPerBox(box);
    }
    return paper;
  }

  public static int paperPerBox(int[] box) {
    int l = box[0];
    int w = box[1];
    int h = box[2];
    //2*l*w + 2*w*h + 2*h*l + the smallest of the preceding 3

    return (2 * l * w) + (2 * w * h) + (2 * h * l) + smallest(l, w, h);
  }

  public static int smallest(int l, int w, int h) {
    int lw = l * w;
    int wh = w * h;
    int hl = h * l;

    return Math.min(lw, Math.min(wh, hl));
  }

  public static List<String> extractLinesFromExamples(String source) {
    List<String> lines = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return lines;
  }
  public static int[] extractNumbersFromLines(String s) {
    String[] parts = s.split("x");
    int[] numbers = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
      numbers[i] = Integer.parseInt(parts[i]);
    }
    return numbers;
  }

}
