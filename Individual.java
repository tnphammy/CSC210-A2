import java.util.ArrayList;
import java.util.Random;

public class Individual {

    /**
     * Chromosome stores the individual's genetic data as an ArrayList of characters
     * Each character represents a gene
     */
    ArrayList<Character> chromosome;

    /**
     * Inital constructor to generate initial population members
     * @param c_0 The initial chromosome size
     * @param num_letters The number of letters available to choose from
     */
    public Individual(int c_0, int num_letters, Random rng) {
        // Make an empty ArrayList to represent the chromosome
        this.chromosome = new ArrayList<Character>(c_0);
        // Loop randomLetters to generate one gene at a time
        for (int i = 0; i < c_0; i++) {
            char newGene = randomLetter(num_letters, rng);
            this.chromosome.add(newGene);
        }
    }

    /**
     * Second constructor to create parents and offspring chromosomes
     * @param parent1 The first parent chromosome
     * @param parent2 The second parent chromosome
     * @param c_max The maximum chromosome size
     * @param m The chances per round of mutation in each gene
     */
    public Individual(Individual parent1, Individual parent2, int c_max, float m, int num_letters, Random rng) {
        // 1. Make an empty ArrayList to represent the chromosome
        this.chromosome = new ArrayList<Character>();

        // 2. Get random prefix & suffix length
        int prefixLength = rng.nextInt(parent1.chromosome.size());
        int suffixLength = rng.nextInt(parent2.chromosome.size());
    
        // 3. Get suffix & prefix => Concatenate them
        ArrayList<Character> prefix = new ArrayList<Character>(parent1.chromosome.subList(0, prefixLength));
        ArrayList<Character> suffix = new ArrayList<Character>(parent2.chromosome.subList(suffixLength, parent2.chromosome.size() - 1));

        this.chromosome.addAll(prefix);
        this.chromosome.addAll(suffix);

        // 4. Check new chromosome size again maximum, trim if necessary
        if (this.chromosome.size() > c_max) {
            this.chromosome = (ArrayList<Character>) this.chromosome.subList(0, c_max);
        }

        // 5. Loop through each gene, determine its mutation
        for (int i = 0; i < this.chromosome.size(); i++) {
            // Determine whether this gene should mutate
            boolean mutationDecision = doesMutate(m, rng);
            if (mutationDecision == true) {
                // Generate the new gene
                char newGene = randomLetter(num_letters, rng);
                // Replace the old gene
                this.chromosome.set(i, newGene);
            }
            else {
                continue;
            }
        }
    }

    /**
     * Provided method to choose a letter at random, in the range from A to the number of states indicated
     * @param num_letters The number of letters available to choose from (number of possible states)
     * @param rng The random number generator being used for the current run
     * @return The letter as a Character
     */
    private Character randomLetter(int num_letters, Random rng) {
        return Character.valueOf((char)(65 + rng.nextInt(num_letters)));
    }

    /**
     * Provided method to determine whether a given gene will mutate based on the parameter ***m***
     * @param m the mutation rate
     * @param rng The random number generator being used for the current run
     * @return true if a number randomly chosen between 0 and 1 is less than ***m***, else false
     */
    private Boolean doesMutate(float m, Random rng) {
        float randomNum = rng.nextInt(100) / 100f;
        return randomNum < m;
    }

    /**
     * Expresses the individual's chromosome as a String, for display purposes
     * @return The chromosome as a String
     */
    public String toString() {
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
            builder.append(ch);
        }
        return builder.toString();
    }




    public static void main(String[] args) {
        // This code will set a random seed when you're testing Individual (i.e., running it without GA_Simulation)
        Random rng = new Random(System.currentTimeMillis());

        // You can pass rng, as defined above, to your constructors.
        Individual parent1 = new Individual(8, 4, rng);
        Individual parent2 = new Individual(8, 4, rng);
        Individual i = new Individual(parent1, parent2, 10, 5, 5, rng);
        System.out.println(i);

    }

}
