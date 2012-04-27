package de.tle.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Population implements Iterable<Individual> {

  protected List<Individual> individuals;
  protected int age = 0;

  public Population() {
    individuals = new ArrayList<Individual>();
  }

  public Population(List<Individual> individuals, int age) {
    this.individuals = individuals;
    this.age = age;
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
  
  public int getAge() {
    return age;
  }

  public List<Individual> getIndividuals() {
    return Collections.unmodifiableList(individuals);
  }
}
