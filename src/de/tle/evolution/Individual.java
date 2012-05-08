package de.tle.evolution;

public class Individual {

  protected Genom genom;
  private Integer fitness;
  private boolean fitnessSet = false;
  private Object customPayload;

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
    sb.append(" (");
    sb.append(customPayload);
    sb.append(")- ");
    sb.append(genom.toString());
    sb.append("} ");
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Individual other = (Individual) obj;
    if (this.genom != other.genom && (this.genom == null || !this.genom.equals(other.genom))) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 79 * hash + (this.genom != null ? this.genom.hashCode() : 0);
    return hash;
  }

  public Object getCustomPayload() {
    return customPayload;
  }

  public void setCustomPayload(Object customPayload) {
    this.customPayload = customPayload;
  }
}