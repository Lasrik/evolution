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
  public void testSort() {
    System.out.println("sort");
    Population instance = new Population();
    instance.sort();
    fail("The test case is a prototype.");
  }

  @Test
  public void testSize() {
    System.out.println("size");
    Population instance = new Population();
    int expResult = 0;
    int result = instance.size();
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testGetAge() {
    System.out.println("getAge");
    Population instance = new Population();
    int expResult = 0;
    int result = instance.getAge();
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testGetFittest() {
    System.out.println("getFittest");
    Population instance = new Population();
    Individual expResult = null;
    Individual result = instance.getFittest();
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testGetIndividuals() {
    System.out.println("getIndividuals");
    Population instance = new Population();
    List expResult = null;
    List result = instance.getIndividuals();
    assertEquals(expResult, result);
    fail("The test case is a prototype.");
  }

  @Test
  public void testAdd() {
    System.out.println("add");
    List<Individual> children = null;
    Population instance = new Population();
    instance.add(children);
    fail("The test case is a prototype.");
  }
}
