package monadlike;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> results = new ArrayList<>();
    for (E e : self) {
      F f = op.apply(e);
      if (f != null) {
        results.add(f);
      }
    }
    return new SuperIterable<>(results);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
