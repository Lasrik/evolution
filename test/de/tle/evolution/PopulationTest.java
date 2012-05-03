package de.tle.evolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.junit.matchers.JUnitMatchers.hasItem;

public class PopulationTest {

  @Mock
  private Individual ind1;
  @Mock
  private Individual ind2;
  @Mock
  private Individual ind3;
  @Mock
  private Individual ind4;
  private Population population;
  private List<Individual> individuals;

  @Before
  public void setUp() {
    population = new Population();
    individuals = new ArrayList(Arrays.asList(ind1, ind2, ind3, ind4));
    population.individuals = individuals;
  }

  @Test
  public void testIterator() {
    Iterator<Individual> referenceIterator = individuals.iterator();
    Iterator<Individual> testIterator = population.iterator();

    while (testIterator.hasNext() && referenceIterator.hasNext()) {
      assertThat(testIterator.next(), is(referenceIterator.next()));
    }

    assertTrue(!referenceIterator.hasNext() && !testIterator.hasNext());
  }

  @Test
  public void testAddIndividual() {
    Individual newIndividual = mock(Individual.class);

    population.addIndividual(newIndividual);

    assertThat(population.individuals, hasItem(newIndividual));
  }

  @Test
  public void testSize() {
    assertThat(population.size(), is(individuals.size()));

    individuals.remove(2);

    assertThat(population.size(), is(individuals.size()));
  }

  @Test
  public void testSizeOfNewPopIs0() {
    population = new Population();
    assertThat(population.size(), is(0));
  }

  @Test
  public void testGetAge() {
    population = new Population(null, 5);
    assertThat(population.getAge(), is(5));
  }

  @Test
  public void testAgeOfNewPopIs0() {
    population = new Population();

    assertThat(population.getAge(), is(0));
  }

  @Test
  public void testGetIndividuals() {
    assertThat(population.getIndividuals(), is(individuals));
  }

  @Test
  public void testAdd() {
    Individual newIndividual = new Individual(null);

    assertThat(population.getIndividuals(), is(individuals));

    population.add(Arrays.asList(newIndividual));

    assertThat(population.individuals, hasItem(newIndividual));
  }
}
