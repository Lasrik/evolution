package de.tle.evolution;

import de.tle.evolution.mutation.Mutation;
import java.util.Comparator;
import java.util.List;

public abstract class Configuration {

  protected Random random = new Random();

  public abstract int getNumberOfChildren();

  public abstract int getNumberOfParents();

  public abstract int getPopulationSize();

  public abstract int getPropabilityOfMutation();

  public abstract FitnessFunction getFitnessFunction();

  public abstract Selector getSelector();

  public abstract GeneticOperator getRecombinationOperator();

  public abstract List<Mutation> getMutations();

  public abstract Factory getFactory();

  public abstract Comparator<Individual> getFitnessComparator();

  public abstract boolean terminationCriteriaMet(Population population);

  public Comparator<Individual> getComparator() {
    return new Comparator<Individual>() {

      @Override
      public int compare(Individual o1, Individual o2) {
        return o1.getFitness() - o2.getFitness();
      }
    };
  }

  public Random getRandom() {
    return this.random;
  }
}
