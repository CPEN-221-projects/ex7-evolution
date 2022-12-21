package evolution;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Evolution {

    /**
     * Compute the number of cells that survive the evolutionary process as
     * described in the problem statement assuming that a given cell dies during
     * the process.
     *
     * @param evolutionaryTree array that represents the normal evolution of the organism
     * @param deadCell         the index in evolutionaryTree of the cell that dies
     * @return the number of surviving cells in the mature organism
     * @requires - evolutionaryTree will contain exactly N elements, where N is
     * an odd integer between 1 and 50, inclusive.
     * - There will be exactly one "-1" element in evolutionaryTree.
     * - Every element of evolutionaryTree will be between -1 and N-1, inclusive.
     * - evolutionaryTree will form a binary tree.
     * - deadCell will be between 0 and N-1, inclusive.
     */
    public static int numSurvivingCells(int[] evolutionaryTree, int deadCell) {
        return getSurvivingCells(evolutionaryTree, deadCell).size();
    }

    /**
     * Compute the number of cells that survive the evolutionary process as
     * described in the problem statement assuming that a given cell dies during
     * the process.
     *
     * @param evolutionaryTree array that represents the normal evolution of the organism
     * @param deadCell         the index in evolutionaryTree of the cell that dies
     * @return the set of surviving cells in the mature organism
     * @requires - evolutionaryTree will contain exactly N elements, where N is
     * an odd integer between 1 and 50, inclusive.
     * - There will be exactly one "-1" element in evolutionaryTree.
     * - Every element of evolutionaryTree will be between -1 and N-1, inclusive.
     * - evolutionaryTree will form a binary tree.
     * - deadCell will be between 0 and N-1, inclusive.
     */
    public static Set<Integer> getSurvivingCells(int[] evolutionaryTree,
                                                 int deadCell) {
        Set<Integer> survivingCells = new HashSet<>();
        for (int i = 0; i < evolutionaryTree.length; i++) {
            survivingCells.add(i);
        }

        checkCells(evolutionaryTree, survivingCells, deadCell, -1);
        return survivingCells;
    }
    private static void checkCells(int[] evolutionaryTree, Set<Integer> survivingCells, int deadCell, int startCell) {
        if (startCell == deadCell) {
            killCells(evolutionaryTree, survivingCells, startCell);
        }
        Set<Integer> cellChildren = seekChildren(evolutionaryTree, survivingCells, startCell);
        if (!cellChildren.isEmpty()) {
            cellChildren.forEach(x -> checkCells(evolutionaryTree, survivingCells, deadCell, x));
            survivingCells.remove(startCell);
        }

    }
    private static void killCells(int[] evolutionaryTree, Set<Integer> survivingCells, int cellKilled) {
        Set<Integer> cellChildren = seekChildren(evolutionaryTree, survivingCells, cellKilled);
        if (cellChildren.isEmpty()) {
            survivingCells.remove(cellKilled);
            return;
        }
        cellChildren.forEach(x -> killCells(evolutionaryTree, survivingCells, x));
        survivingCells.remove(cellKilled);
    }
    private static Set<Integer> seekChildren(int[] evolutionaryTree, Set<Integer> survivingCells, int cellKilled) {
        return survivingCells.stream().filter(x -> evolutionaryTree[x] == cellKilled).collect(Collectors.toSet());
    }
}
