package monadlike;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class UseStream {
//  public static Stream<String> getData(String path) {
//    try {
//      return Files.lines(Path.of(path));
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//  }
  public static Optional<Stream<String>> getData(String path) {
    try {
      return Optional.of(Files.lines(Path.of(path)));
    } catch (IOException e) {
      return Optional.empty();
    }
  }
  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "data.txt")
//        .flatMap(n -> UseStream.getData(n)) // flatMap belongs in a Monad
        .map(n -> UseStream.getData(n))
        .peek(s -> {
          if (s.isEmpty()) System.out.println("Problem with a file");
        })
        .filter(o -> o.isPresent())
        .flatMap(o -> o.get())
        .forEach(s -> System.out.println(s));
  }
}
