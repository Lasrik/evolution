package de.tle.evolution;

public class EvolutionAlgorithm {
  /*
  http://de.wikipedia.org/wiki/Evolutionsstrategie#Definition
  http://de.wikipedia.org/wiki/Genetischer_Algorithmus#Praktisches_Vorgehen
  http://de.wikipedia.org/wiki/Evolutionsstrategie#Arten
   */

  protected Configuration config;
  protected Population population;

  public EvolutionAlgorithm(Configuration config) {
    this.config = config;
  }

  public void evolve() {
    initPopulation();
    calculateFitness();

    do {
      recombine();
      mutate();
      calculateFitness();
      selectNextGeneration();
    } while (terminationCriteriaNotMet());
  }

  private void initPopulation() {
    population = new Population();
    for (int i = 0; i < config.populationSize; i++) {
      Individual ind = config.getFactory().createRandomIndividual();
      population.addIndividual(ind);
    }
  }

  private void calculateFitness() {
    for (Individual individual : population) {
      config.getFitnessFunction().evaluate(individual);
    }
  }

  private void recombine() {
    for (int i = 0; i < config.numberOfChildren; i++) {
      Individual[] parents = config.getSelector().select(population);
      Individual child = config.getRecombinationOperator().operate(parents);
      population.addIndividual(child);
    }
  }

  private void mutate() {
    for (Individual individual : population) {
      if (mutationTakesPlace()) {
        Mutation m = chooseMutation();
        m.mutate(individual);
      }
    }
  }

  private void selectNextGeneration() {
    population.sort();

  }

  private boolean terminationCriteriaNotMet() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private boolean mutationTakesPlace() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  private Mutation chooseMutation() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
