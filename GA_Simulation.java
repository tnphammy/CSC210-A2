import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GA_Simulation {

  // Use the instructions to identify the class variables, constructors, and
  // methods you need
  public static Random rng;
  protected ArrayList<Individual> pop = new ArrayList<>();
  /** The number of Individuals in each generation */
  protected int n;
  /** The number of winners (for reproduction purposes) */
  protected int k;
  /** The number of rounds for evolution */
  protected int r;
  protected int roundNumber = 0;
  /** The initial size of a chromosome */
  protected int c_0;
  /** The maximum size of a chromosome */
  protected int c_max;
  /** The chance per round of a mutation in each gene */
  protected float m;
  /** The number of states possible per gene */
  protected int g;

  /* CONSTRUCTOR */
  /**
   * Constructor of a population
   * 
   * @param n     The number of Individuals in each generation
   * @param k     The number of winners (for reproduction purposes)
   * @param r     The number of rounds for evolution
   * @param c_0   The initial size of a chromosome
   * @param c_max The maximum size of a chromosome
   * @param m     The chance per round of a mutation in each gene
   * @param g     The number of states possible per gene
   */
  public GA_Simulation(int n, int k, int r, int c_0, int c_max, float m, int g) {
    this.n = n;
    this.k = k;
    this.r = r;
    this.c_0 = c_0;
    this.c_max = c_max;
    this.m = m;
    this.g = g;
  }

  /**
   * Initialize the population
   */
  public void init() {
    // Create n number of Individuals
    for (int i = 0; i <= n; i++) {
      Individual newIndividual = new Individual(c_0, g, rng);
      pop.add(newIndividual);
    }
  }

  /**
   * Provided method that prints out summary statistics for a given
   * generation, based on the information provided
   * 
   * @param roundNumber:  Which round of evolution are we on, from 0 to n
   * @param bestFitness:  Fitness of top-ranked (most fit) individual
   * @param kthFitness:   Fitness of kth-ranked individual
   * @param leastFitness: Fitness of lowest-ranked (least fit) individual
   * @param best:         Individual with highest fitness
   * @return: Nothing, prints statistics to standard out
   */
  private void printGenInfo(int roundNumber, int bestFitness, int kthFitness, int leastFitness, Individual best) {
    System.out.println("Round " + roundNumber + ":");
    System.out.println("Best fitness: " + bestFitness);
    System.out.println("k-th (" + k + ") fitness: " + kthFitness);
    System.out.println("Least fit: " + leastFitness);
    System.out.println("Best chromosome: " + best);
    System.out.println(); // blank line to match the example format
  }

  /**
   * Provided method that sorts population by fitness score, best first
   * 
   * @param pop ArrayList of Individuals in the current generation
   * @return Nothing. ArrayList is sorted in place
   */
  public void rankPopulation(ArrayList<Individual> pop) {
    // sort population by fitness
    Comparator<Individual> ranker = new Comparator<>() {
      // this order will sort higher scores at the front
      public int compare(Individual c1, Individual c2) {
        return (int) Math.signum(c2.getFitness() - c1.getFitness());
      }
    };
    pop.sort(ranker);
  }

  /**
   * Evolve current generation to create offspring based on natural selection
   * 
   * @param rng The random seed
   */
  public void evolve() {
    // 1. Select top k
    ArrayList<Individual> topK = new ArrayList<Individual>(pop.subList(0, k));
    // 2. Producing offspring from winners (Looped n times for desired population
    // count)
    for (int i = 0; i <= n; i++) {
      // Select 2 parents
      Individual parent1 = topK.get(rng.nextInt(k));
      Individual parent2 = topK.get(rng.nextInt(k));
      // Make an offspring from both parents
      Individual offspring = new Individual(parent1, parent2, c_max, m, g, rng);
      // Add offspring to current population
      pop.add(offspring);
    }
  }

  /**
   * Print some statistics about the current generation.
   * Specifically, show the fitness of the fittest individual in the generation
   * and its chromosome,
   * the kth individual,
   * and the least fit (last ranking) individual,
   */
  public void describeGeneration() {
    // 1. Describe fittest Individual
    Individual best = this.pop.get(0);
    int bestFitness = best.getFitness();
    // 2. Describe kth Individual
    int kthFitness = this.pop.get(k).getFitness();
    // 3. Describe least fit Individual
    int leastFitness = this.pop.get(pop.size() - 1).getFitness();
    // 4. Print out information
    printGenInfo(roundNumber, bestFitness, kthFitness, leastFitness, best);
  }

  /** Runs the entire experiment */
  public void run() {
    // INITIAL ROUND
    // 1. Initialize population
    init();
    roundNumber = 1;
    // 2. Rank the population
    rankPopulation(pop);
    // 3. Describe the population
    describeGeneration();

    // EVOLUTIONARY ROUNDS
    // Loop evolution r number of times
    for (int i = 0; i < r - 1; i++) {
      roundNumber++;
      evolve();
      rankPopulation(pop);
      describeGeneration();
    }
  }

  public static void main(String[] args) {
    // This first block of code establishes a random seed, which will make
    // it easier to test your code. The output should remain consistent if the
    // seed is the same. To run with a specific seed, you can run from the
    // command line like:
    // java GA_Simulation 24601

    long seed = System.currentTimeMillis(); // default
    if (args.length > 0) {
      try {
        seed = Long.parseLong(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("Seed wasn't passed so using current time.");
      }
    }
    rng = new Random(seed);

    // Write your main below:
    // GA_Simulation life = new GA_Simulation(100, 20, 70, 8, 20, 0.01f, 5);
    // GA_Simulation life = new GA_Simulation(100, 15, 60, 8, 20, 0.01f, 5);
    // GA_Simulation life = new GA_Simulation(100, 15, 60, 8, 20, 1, 5);
    GA_Simulation life = new GA_Simulation(100, 15, 100, 8, 20, 0.01f, 5);
    System.out.println("Population size: " + life.pop.size());
    life.run();
    System.out.println("Population size: " + life.pop.size());
    System.out.println("First creature: " + life.pop.get(0));
  }

}
