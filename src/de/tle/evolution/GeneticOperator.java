package de.tle.evolution;

public interface GeneticOperator {

  Individual operate(Individual... selectedIndividuals);
}
