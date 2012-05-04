package de.tle.evolution;

public class Individual {

  protected Genom genom;
  private int fitness;
  private Object payload;

  public Individual(Genom genom) {
    this.genom = genom;
    this.fitness = Integer.MIN_VALUE;
  }

  public static Individual createRandom() {
    return new Individual(Genom.createRandom());
  }

  public Genom getGenom() {
    return genom;
  }

  public void setGenom(Genom genom) {
    this.genom = genom;
  }

  public int getFitness() {
    return fitness;
  }

  public void setFitness(int fitness) {
    this.fitness = fitness;
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

  public Object getPayload() {
    return payload;
  }

  public void setPayload(Object payload) {
    this.payload = payload;
  }
}