package monadlike;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@FunctionalInterface
interface ExFunction<A, B> {
  B apply(A a) throws Throwable;
}

class Either<L, R> {
  private L left;
  private R right;

  private Either(L left, R rigth) {
    this.left = left;
    this.right = rigth;
  }

  public void useFailure(Consumer<L> op) {
    if (left != null) op.accept(left);
  }

  public boolean isSuccess() {
    return left == null;
  }

  public R get() {
    if (left != null) throw new NoSuchElementException("can't get nonexistent success!");
    return right;
  }

  public static <L, R> Either<L, R> success(R r) {
    return new Either<>(null, r);
  }

  public static <L, R> Either<L, R> failure(L l) {
    return new Either<>(l, null);
  }

  public Either<L, R> recover(Function<L, Either<L, R>> op) {
    if (left != null) {
      return op.apply(left);
    } else return this;
  }

  public static <A, B> Function<A, Either<Throwable, B>> wrap(ExFunction<A, B> op) {
    return a -> {
      try {
        return Either.success(op.apply(a));
      } catch (Throwable throwable) {
        return Either.failure(throwable);
      }
    };
  }
}

public class UsingEither {
//  public static Either<Throwable, Stream<String>> getData(String path) {
//    try {
//      return Either.success(Files.lines(Path.of(path)));
//    } catch (IOException e) {
//      return Either.failure(e);
//    }
//  }

  public static void main(String[] args) {
    Stream.of("a.txt", "b.txt", "data.txt")
        .map(Either.wrap(n -> Files.lines(Path.of(n))))
        .peek(e -> e.useFailure(f -> System.out.println("Failed: " + f.getMessage())))
        .map(e -> e.recover(Either.wrap(l -> Files.lines(Path.of("recover.txt")))))
        .filter(e -> e.isSuccess())
        .flatMap(o -> o.get())
        .forEach(s -> System.out.println(s));
  }

}
