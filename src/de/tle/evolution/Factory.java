package de.tle.evolution;

public abstract class Factory {

    public abstract Individual createRandomIndividual();

    public Population createInitialPopulation(int populationSize) {
        Population population = new Population();
        for (int i = 0; i < populationSize; i++) {
            Individual ind = createRandomIndividual();
            population.addIndividual(ind);
        }
        
        return population;
    }
}
