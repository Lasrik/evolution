package de.tle.evolution.mutation;

import de.tle.evolution.Individual;

public class SwitchMutation implements Mutation {

  @Override
  public void mutate(Individual individual) {
    individual.getGenom().switchRandomChromosomes();
  }
  
}
