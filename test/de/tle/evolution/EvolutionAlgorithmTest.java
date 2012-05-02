package de.tle.evolution;

import de.tle.evolution.mutation.Mutation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import static de.tle.evolution.util.IsIterableContainingInOrder.contains;

public class EvolutionAlgorithmTest {

  @Mock
  private Configuration config;
  @Mock
  private Random random;
  @Mock
  private Mutation mut1;
  @Mock
  private Mutation mut2;
  @Mock
  private Mutation mut3;
  @Mock
  private Mutation mut4;
  @Mock
  private Individual child1;
  @Mock
  private Individual child2;
  @Mock
  private Individual child3;
  @Mock
  private Individual child4;
  private List<Individual> children;
  private EvolutionAlgorithm ea;
  private List<Mutation> mutations;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    ea = new EvolutionAlgorithm(config);

    when(config.getRandom()).thenReturn(random);

    mutations = Arrays.asList(mut1, mut2, mut3, mut4);
    when(config.getMutations()).thenReturn(mutations);

    children = Arrays.asList(child1, child2, child3, child4);
  }

  @Test
  public void testChooseMutation() {
    when(random.getNextInt(anyInt())).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(3);

    Mutation result = ea.chooseMutation();
    assertThat(result, is(mut1));

    result = ea.chooseMutation();
    assertThat(result, is(mut2));

    result = ea.chooseMutation();
    assertThat(result, is(mut3));

    result = ea.chooseMutation();
    assertThat(result, is(mut4));
  }

  @Test
  public void testChooseMutationRandomIsMutationsSize() {
    ea.chooseMutation();

    ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
    verify(random).getNextInt(argument.capture());

    assertThat(argument.getValue(), is(mutations.size()));
  }

  @Test
  public void testMutationTakesPlace() {
    when(random.getNextPercentage()).thenReturn(50);

    // 100% - immer mutieren
    when(config.getPropabilityOfMutation()).thenReturn(100);
    boolean result = ea.mutationTakesPlace();
    assertThat(result, is(true));

    // 0% - nie mutieren
    when(config.getPropabilityOfMutation()).thenReturn(0);
    result = ea.mutationTakesPlace();
    assertThat(result, is(false));

    when(random.getNextPercentage()).thenReturn(5);
    when(config.getPropabilityOfMutation()).thenReturn(10);
    result = ea.mutationTakesPlace();
    assertThat(result, is(true));

    when(random.getNextPercentage()).thenReturn(10);
    when(config.getPropabilityOfMutation()).thenReturn(5);
    result = ea.mutationTakesPlace();
    assertThat(result, is(false));
  }

  @Test
  public void testTerminationCriteriaNotMet() {
    when(config.terminationCriteriaMet(any(Population.class))).thenReturn(false);
    assertThat(ea.terminationCriteriaNotMet(), is(true));

    when(config.terminationCriteriaMet(any(Population.class))).thenReturn(true);
    assertThat(ea.terminationCriteriaNotMet(), is(false));
  }

  @Test
  public void testSelectNextGeneration() {
    Population oldPopulation = mock(Population.class);
    Population newPopulation = mock(Population.class);

    ea.population = oldPopulation;

    Selector selector = mock(Selector.class);
    when(selector.selectNextGeneration(oldPopulation)).thenReturn(newPopulation);
    when(config.getSelector()).thenReturn(selector);

    ea.selectNextGeneration();

    assertThat(ea.population, is(newPopulation));
  }

  @Test
  public void testMutate() {
    when(config.getPropabilityOfMutation()).thenReturn(100);
    when(random.getNextPercentage()).thenReturn(50);
    when(random.getNextInt(anyInt())).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(1);

    ea.mutate(children);

    verify(mut1).mutate(child1);
    verify(mut2).mutate(child2);
    verify(mut3).mutate(child3);
    verify(mut2).mutate(child4);
  }

  @Test
  public void testMutate2() {
    when(config.getPropabilityOfMutation()).thenReturn(50);
    when(random.getNextPercentage()).thenReturn(30).thenReturn(60).thenReturn(30);
    when(random.getNextInt(anyInt())).thenReturn(0).thenReturn(1).thenReturn(2);

    ea.mutate(children);

    verify(mut1).mutate(child1);
    verify(mut2).mutate(child3);
    verify(mut3).mutate(child4);
  }

  @Test
  public void testRecombine() {
    Selector selector = mock(Selector.class);
    List<List<Individual>> parents = new ArrayList<List<Individual>>(children.size());
    List<Individual> parent = mock(List.class);
    for (int i = 0; i < children.size(); i++) {
      parents.add(parent);
    }
    
    when(selector.selectParents(any(Population.class))).thenReturn(parents);
    
    GeneticOperator operator = mock(GeneticOperator.class);
    
    when(operator.operate(anyList())).thenReturn(child1, child2, child3, child4);
    
    List<Individual> actualChildren = ea.recombine();
  }
}