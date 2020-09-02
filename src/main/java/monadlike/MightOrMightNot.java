package monadlike;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MightOrMightNot {
  public static void main(String[] args) {
    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");

    String first = "Fred"; // pretend this was calculated...

    System.out.println("--------------");
    String lastName = names.get(first);
    if (lastName != null) {
      String caps = lastName.toUpperCase();
      // if ...
      String message = "Dear " + caps;
      // if ...
      System.out.println(message);
    }
    System.out.println("--------------");

    SuperIterable<Map<String, String>> simss = new SuperIterable<>(Arrays.asList(names));
    simss
        .map(m -> m.get(first))
        .map(n -> n.toUpperCase())
        .map(n -> "Dear " + n)
        .forEach(s -> System.out.println(s));
    System.out.println("--------------");

    Optional<Map<String, String>> opt = Optional.of(names);
    opt
        .map(m -> m.get(first))
        .map(n -> n.toUpperCase())
        .map(n -> "Dear " + n)
        .ifPresent(s -> System.out.println(s));
    System.out.println("--------------");
  }
}
