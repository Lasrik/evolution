package de.tle.evolution;

public class Random {

  protected java.util.Random random;
  
  public Random() {
    this.random = new java.util.Random();
  }
  
  public int getNextPercentage() {
    return getNextInt(100) + 1;
  }
  
  public int getNextInt(int max) {
    return random.nextInt(max);
  }
}
