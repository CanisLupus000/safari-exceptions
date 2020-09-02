package monadlike;

import java.util.Arrays;
import java.util.List;

public class ProcessList {
  public static void main(String[] args) {
    SuperIterable<String> ls = new SuperIterable<>(Arrays.asList("Fred", "Jim", "Sheila"));
    for (String s : ls) {
      System.out.println("> " + s);
    }
    ls
        .map(s -> s.length())
        .forEach(v -> System.out.println(v));
//    .map(i -> {
//      System.out.println("Length:> " + i);
//      return null;
//      });
    System.out.println("------");
    new SuperIterable<String>(Arrays.asList())
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println(s));
    System.out.println("------");
  }
}
