package de.tle.evolution;

import java.util.List;

public interface GeneticOperator {

  Individual operate(List<Individual> selectedIndividuals);
}
