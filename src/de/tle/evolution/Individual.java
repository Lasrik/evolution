package de.tle.evolution;

public class Individual {

  protected Genom genom;
  private volatile Integer fitness;
  private boolean fitnessSet = false;

  public Individual(Genom genom) {
    this.genom = genom;
    this.fitness = null;
  }

  public static Individual createRandom(int numberOfChromosomes, int maxCrossSum, int maxSingleValue) {
    return new Individual(Genom.createRandom(numberOfChromosomes, maxCrossSum, maxSingleValue));
  }

  public Genom getGenom() {
    return genom;
  }

  public void setGenom(Genom genom) {
    this.genom = genom;
  }

  public Integer getFitness() {
    return fitness;
  }

  public void setFitness(Integer fitness) {
    this.fitnessSet = true;
    this.fitness = fitness;
  }

  public boolean isFitnessSet() {
    return fitnessSet;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{ -");
    sb.append(fitness);
    sb.append("- [");
    sb.append(genom.toString());
    sb.append("]} ");
    return sb.toString();
  }
}