package de.tle.evolution;

public abstract class FitnessFunction {

  public abstract void evaluate(Individual ind);

  public void evaluate(Population population) {
    for (Individual individual : population) {
      evaluate(individual);
    }
  }
}
