package de.tle.evolution;

import de.tle.evolution.mutation.Mutation;
import java.util.List;
import org.apache.log4j.Logger;

public class EvolutionAlgorithm {
  /*
   * http://de.wikipedia.org/wiki/Evolutionsstrategie#Definition
   * http://de.wikipedia.org/wiki/Genetischer_Algorithmus#Praktisches_Vorgehen
   * http://de.wikipedia.org/wiki/Evolutionsstrategie#Arten
   */

  protected Configuration config;
  protected Population population;
  protected Logger log;

  public EvolutionAlgorithm(Configuration config) {
    this.config = config;
    log = Logger.getLogger(getClass());
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

  protected void initPopulation() {
    log.trace("init Population");
    population = config.getFactory().createInitialPopulation(config.getPopulationSize());
  }

  protected void calculateFitness() {
    log.trace("calculate Fitness");
    config.getFitnessFunction().evaluate(population);
    log.debug(population);
  }

  protected void recombine() {
    log.trace("recombine");
    List<List<Individual>> allParents = config.getSelector().selectParents(population);
    for (List<Individual> parents : allParents) {
      Individual child = config.getRecombinationOperator().operate(parents);
      population.addIndividual(child);
    }
    log.debug("Recombine: " + population);
  }

  protected void mutate() {
    log.trace("mutate");
    for (Individual individual : population) {
      if (mutationTakesPlace()) {
        Mutation m = chooseMutation();
        m.mutate(individual);
      }
    }
  }

  protected void selectNextGeneration() {
    log.trace("select next generation");
    population = config.getSelector().selectNextGeneration(population);
    log.debug("Next gen: " + population);
  }

  protected boolean terminationCriteriaNotMet() {
    return !config.terminationCriteriaMet(population);
  }

  protected boolean mutationTakesPlace() {
    int propability = config.getPropabilityOfMutation();
    return config.getRandom().getNextPercentage() <= propability;
  }

  protected Mutation chooseMutation() {
    List<Mutation> mutations = config.getMutations();
    int number = config.getRandom().getNextInt(mutations.size());
    
    return mutations.get(number);
  }
}
