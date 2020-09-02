package trywithresources;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Example {
  public static void main(String[] args) throws Throwable {
    // "resource" must implement AutoCloseable
    // finally is implicit and closes all resources
    try (BufferedReader input = Files.newBufferedReader(Path.of("data.txt"));) {
      String line;
      while ((line = input.readLine()) != null) {
        System.out.println("> " + line);
      }
    } catch (IOException ioe) {
      System.out.println("Broke: " + ioe.getMessage());
    }
  }
//  public static void main(String[] args) throws Throwable {
//    BufferedReader input = null;
//    try {
//      input = Files.newBufferedReader(Path.of("data.txt"));
//      String line;
//      while ((line = input.readLine()) != null) {
//        System.out.println("> " + line);
//      }
//    } catch (IOException ioe) {
//      System.out.println("Broke: " + ioe.getMessage());
//    } finally {
//      // here we close the file...
//      if (input != null) {
//        try {
//          input.close();
//        } catch (IOException e2) {// on close?
//        }
//      }
//    }
//  }
}
