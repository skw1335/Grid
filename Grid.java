// Grid.java, for pa3

import java.util.Set;
import java.util.HashSet;

/**
 * Class to demonstrate greedy algorithms
 */
public class Grid {
    private boolean[][] grid = null;
    private int count = 0;
    private Set<Spot> set = new HashSet<Spot>();
    private int groupcounter = 0;
    /**
     * Very simple constructor
     * 
     * @param ingrid
     *            a two-dimensional array of boolean to be used as the grid to
     *            search
     */
    public Grid(boolean[][] ingrid) {
        grid = ingrid;
    }

    /**
     * Main method, creates a Grid, then asks it for the size of the group
     * containing the given point.
     */
    public static void main(String[] args) {
        int i = 0;
        int j = 0;

        // Make sure we've got the right number of arguments
        if (args.length != 2) {
            System.err.println("Incorrect arguments.");
            printUsage();
            return;
        } else {
            i = Integer.parseInt(args[0]);
            j = Integer.parseInt(args[1]);
        }

        // Usage: java Grid 3 7 to search from (3, 7), top occupied square
        // of L-shaped group of Figure 7.30, pg. 281

        boolean[][] gridData = {
                { false, false, false, false, false, false, false, false,
                        false, true },
                { false, false, false, true, true, false, false, false, false,
                        true },
                { false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, true, false, false, true, false,
                        false },
                { false, false, false, true, false, false, false, true, false,
                        false },
                { false, false, false, false, false, false, false, true, true,
                        false },
                { false, false, false, false, true, true, false, false, false,
                        false },
                { false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false },
                { false, false, false, false, false, false, false, false,
                        false, false } };

        Grid mygrid = new Grid(gridData);
	System.out.println("First Group: "); 
        int size = mygrid.groupSize(i, j);       
        System.out.println("Group size: " + size);
	System.out.println("All other groups");
        int foo = mygrid.numberOfGroups();
        System.out.println("number of groups = " + foo);
    }

    /**
     * Prints out a usage message
     */
    private static void printUsage() {
        System.out.println("Usage: java Grid <i> <j>");
        System.out
                .println("Find the size of the cluster of spots including position i,j");
    }

    /**
     * This calls the recursive method to find the group size
     * 
     * @param i
     *            the first index into grid (i.e. the row number)
     * @param j
     *            the second index into grid (i.e. the col number)
     * @return the size of the group
     */
    public int groupSize(int i, int j) {
        
        if(grid[i][j] == true) {
            Spot spot = new Spot(i,j); 
            set.add(spot); 
            System.out.println(spot.toString());
            grid[i][j] = false;
            groupSize(i,j);
        }
        if(grid[i][j] == false) {
            if(i < 9 && grid[i+1][j] == true) {
            i = i + 1;
            Spot spot = new Spot(i,j);
            set.add(spot); 
            if (!set.contains(spot)) {
                System.out.println(spot.toString());
                }
             //  grid[i][j] = false;
              //  System.out.println("i = " + i);    
             // System.out.println("recursion 2");
            groupSize(i,j); 
            }
            if(i > 0 && grid[i-1][j] == true) {
            i = i - 1;
            Spot spot = new Spot(i,j);
            set.add(spot);
                if (!set.contains(spot)) {
                System.out.println(spot.toString());
                }
            // grid[i][j] = false;
            groupSize(i,j);
            }
            if(j < 9 && grid[i][j+1] == true) {
            j = j + 1;
            Spot spot = new Spot(i,j);
            set.add(spot); 
            if (!set.contains(spot)) {
                System.out.println(spot.toString());
                }
            // grid[i][j] = false;
            groupSize(i,j);
            }
            if(j < 9 && grid[i][j-1] == true) {
            j = j - 1;
            Spot spot = new Spot(i,j);
            set.add(spot);
            if (!set.contains(spot)) {
                System.out.println(spot.toString());
                }
            // grid[i][j] = false;
            groupSize(i,j);
            }
            
        }

        return set.size();
    }
    public int numberOfGroups() {
       
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){ 
                if (grid[i][j] == true) {
                    
                    if (groupSize(i,j) > 0)
                    
                    groupcounter++;
                }
            }
        }
        return groupcounter + 1; 
    }
    /**
     * Nested class to represent a filled spot in the grid
     */
    private static class Spot {
        int i;
        int j;

        /**
         * Constructor for a Spot
         * 
         * @param i
         *            the i-coordinate of this Spot
         * @param j
         *            the j-coordinate of this Spot
         */
        public Spot(int i, int j) {
            this.i = i;
            this.j = j;
        }

        /**
         * Tests whether this Spot is equal (i.e. has the same coordinates) to
         * another
         * 
         * @param o
         *            an Object
         * @return true if o is a Spot with the same coordinates
         */
        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (o.getClass() != getClass())
                return false;
            Spot other = (Spot) o;
            return (other.i == i) && (other.j == j);

        }

        /**
         * Returns an int based on Spot's contents
         * another way: (new Integer(i)).hashCode()^(new Integer(j)).hashCode();
         */
        public int hashCode() {
            return i << 16 + j; // combine i and j two halves of int
        }

        /**
         * Returns a String representing this Spot
         */
        public String toString() {
            return "(" + i + " , " + j + ")";
        }
    }
}
