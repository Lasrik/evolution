package de.tle.evolution.mutation;

import de.tle.evolution.Individual;

public class DecrementMutation implements Mutation {

  @Override
  public void mutate(Individual individual) {
    individual.getGenom().reduceRandomChromosom();
  }
  
}
