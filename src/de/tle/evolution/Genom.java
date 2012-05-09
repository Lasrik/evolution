package de.tle.evolution;

import java.util.Arrays;

public class Genom {

  private int numberOfChromosomes = 7;
  private int maxCrossSum = 250;
  private int maxSingleValue = 250;
  private int[] chromosomes;

  public Genom(int[] chromosomes) {
    this.chromosomes = chromosomes;
  }

  public Genom(int numberOfChromosomes, int maxCrossSum, int maxSingleValue) {
    this.numberOfChromosomes = numberOfChromosomes;
    this.maxCrossSum = maxCrossSum;
    this.maxSingleValue = maxSingleValue;
    this.chromosomes = new int[numberOfChromosomes];
  }

  public static Genom createRandom(int numberOfChromosomes, int maxCrossSum, int maxSingleValue) {
    Genom instance = new Genom(numberOfChromosomes, maxCrossSum, maxSingleValue);
    instance.createRandomChromosomes();
    return instance;
  }

  public int[] getChromosomes() {
    return chromosomes;
  }

  public int getChromosom(int position) {
    return chromosomes[position];
  }

  public void setChromosom(int position, int value) {
    chromosomes[position] = value;
  }

  public void setChromosomes(int[] chromosomes) {
    this.chromosomes = chromosomes;
  }

  public Genom crossover(Genom other) {
    int[] newChromosomes = new int[numberOfChromosomes];
    int randomPosition = getRandomChromosomPosition();

    firstPartFromThisGenom(newChromosomes, randomPosition);
    secondPartFromOtherGenom(other, randomPosition, newChromosomes);
    Genom result = new Genom(newChromosomes);

    return result;
  }

  public void reduceRandomChromosom() {
    if (crossSum() < 2) {
      return;
    }

    boolean end = false;
    do {
      int random = getRandomChromosomPosition();
      if (chromosomes[random] > 0) {
        chromosomes[random] -= 1;
        end = true;
      }
    } while (!end);
  }

  public void increaseRandomChromosom() {
    if (crossSum() >= maxCrossSum) {
      return;
    }

    boolean end = false;
    do {
      int random = getRandomChromosomPosition();
      if (chromosomes[random] < maxSingleValue) {
        chromosomes[random] += 1;
        end = true;
      }
    } while (!end);
  }

  public void switchRandomChromosomes() {
    int rand1 = getRandomChromosomPosition();
    int rand2 = getDifferentRandomChromosomPosition(rand1);

    int tmp = chromosomes[rand1];
    chromosomes[rand1] = chromosomes[rand2];
    chromosomes[rand2] = tmp;
  }

  public boolean isValid() {
    int crossSum = crossSum();
    if (crossSum > 0 && crossSum <= maxCrossSum) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    return Arrays.toString(chromosomes);
  }

  protected int getRandomChromosomPosition() {
    return getRandomInt(chromosomes.length);
  }

  private int getDifferentRandomChromosomPosition(int rand1) {
    int rand2 = getRandomChromosomPosition();
    while (rand1 == rand2) {
      rand2 = getRandomChromosomPosition();
    }
    return rand2;
  }

  protected int createRandomChromosomeValue() {
    return (int) (getRandom() * maxSingleValue);
  }

  protected double getRandom() {
    return Math.random();
  }

  protected int getRandomInt(int ceiling) {
    return (int) (getRandom() * ceiling);
  }

  protected void createRandomChromosomes() {
    int[] newChromosomes = new int[numberOfChromosomes];

    int crossSum = 0;
    boolean startFromEnd = false;

    if (getRandom() < 0.5) {
      startFromEnd = true;
    }

    for (int i = 0; i < newChromosomes.length; i++) {
      if (getRandom() < 0.25) {
        continue;
      }

      int nextValue = createRandomChromosomeValue();
      if (crossSum + nextValue > maxCrossSum) {
        nextValue = maxCrossSum - crossSum;
      }

      crossSum += nextValue;

      int position = i;
      if (startFromEnd) {
        position = newChromosomes.length - (i + 1);
      }

      newChromosomes[position] = nextValue;
    }

    this.chromosomes = newChromosomes;
  }

  public int crossSum() {
    int result = 0;
    for (int chromosom : chromosomes) {
      result += chromosom;
    }
    return result;
  }

  private void secondPartFromOtherGenom(Genom other, int randomPosition, int[] newChromosomes) {
    System.arraycopy(other.chromosomes, randomPosition, newChromosomes, randomPosition, newChromosomes.length - randomPosition);
  }

  private void firstPartFromThisGenom(int[] newChromosomes, int randomPosition) {
    System.arraycopy(this.chromosomes, 0, newChromosomes, 0, randomPosition);
  }

  public int length() {
    return this.chromosomes.length;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Genom other = (Genom) obj;
    if (!Arrays.equals(this.chromosomes, other.chromosomes)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 59 * hash + Arrays.hashCode(this.chromosomes);
    return hash;
  }
}
