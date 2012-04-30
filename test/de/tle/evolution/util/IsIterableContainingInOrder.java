package de.tle.evolution.util;

import java.util.Collection;
import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public class IsIterableContainingInOrder<E> extends TypeSafeMatcher<Iterable<E>> {

  private final Collection<Matcher<? super E>> matchers;

  public IsIterableContainingInOrder(Collection<Matcher<? super E>> contents) {
    if (contents.isEmpty()) {
      throw new IllegalArgumentException("Should specify at least one expected element");
    }

    this.matchers = contents;
  }

  @Override
  public boolean matchesSafely(Iterable<E> iterable) {
    Iterator<E> items = iterable.iterator();
    Iterator<Matcher<? super E>> matchersIterator = matchers.iterator();
    while (items.hasNext() && matchersIterator.hasNext()) {
      if (!matchersIterator.next().matches(items.next())) {
        return false;
      }
    }
    return !items.hasNext() && !matchersIterator.hasNext();

  }

  @Override
  public void describeTo(Description description) {
    description.appendText("is iterable containing in order: ").appendList("[ ", ", ", " ]", matchers);

  }

  @Factory
  public static <E> Matcher<Iterable<E>> contains(Collection<Matcher<? super E>> contents) {
    return new IsIterableContainingInOrder<E>(contents);
  }
}
