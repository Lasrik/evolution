package de.tle.evolution;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population implements Iterable<Individual> {

  protected List<Individual> individuals;
  protected int generations = 0;

  public Population() {
  }

  @Override
  public Iterator<Individual> iterator() {
    return individuals.listIterator();
  }

  public void addIndividual(Individual individual) {
    individuals.add(individual);
  }

  public void sort() {
    Collections.sort(individuals);
  }

  public int size() {
    return individuals.size();
  }

  public void selectFittest(int number) {
    List<Individual> newGeneration = individuals.subList(0, number);
    generations++;
    individuals = newGeneration;
  }
}
