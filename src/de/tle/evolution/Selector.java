package de.tle.evolution;

import java.util.List;

public interface Selector {

  List<List<Individual>> selectParents(Population fromPopulation);

  Population selectNextGeneration(Population fromPopulation);
}