package de.tle.evolution.impl;

import de.tle.evolution.Individual;
import de.tle.evolution.Population;
import de.tle.evolution.Selector;
import java.util.List;

public class FittestSelector implements Selector {

  @Override
  public List<List<Individual>> selectParents(Population fromPopulation) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Population selectNextGeneration(Population population) {
    int number = population.size() / 2;
    List<Individual> newGeneration = population.getIndividuals().subList(0, number);
    int age = population.getAge() + 1;

    Population result = new Population(newGeneration, age);

    return result;
  }
}
