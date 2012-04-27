package de.tle.evolution.mutation;

import de.tle.evolution.Individual;

public class IncrementMutation implements Mutation {

  @Override
  public void mutate(Individual individual) {
    individual.getGenom().increaseRandomChromosom();
  }
  
}
