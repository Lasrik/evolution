package de.tle.evolution;

import java.util.Comparator;
import java.util.List;

public abstract class Configuration {

  protected int populationSize;
  protected int numberOfParents;
  protected int numberOfChildren;
  protected int propabilityOfMutation;

  public int getNumberOfChildren() {
    return numberOfChildren;
  }

  public void setNumberOfChildren(int numberOfChildren) {
    this.numberOfChildren = numberOfChildren;
  }

  public int getNumberOfParents() {
    return numberOfParents;
  }

  public void setNumberOfParents(int numberOfParents) {
    this.numberOfParents = numberOfParents;
  }

  public int getPopulationSize() {
    return populationSize;
  }

  public void setPopulationSize(int populationSize) {
    this.populationSize = populationSize;
  }

  public int getPropabilityOfMutation() {
    return propabilityOfMutation;
  }

  public void setPropabilityOfMutation(int propabilityOfMutation) {
    this.propabilityOfMutation = propabilityOfMutation;
  }

  public abstract FitnessFunction getFitnessFunction();

  public abstract Selector getSelector();

  public abstract GeneticOperator getRecombinationOperator();

  public abstract List<Mutation> getMutations();

  public abstract Factory getFactory();

  public abstract Comparator<Individual> getFitnessComparator();
}
