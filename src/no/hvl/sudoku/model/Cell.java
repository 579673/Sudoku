package no.hvl.sudoku.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cell class for a sudoku game. Stores information about the cells in the Sudoku grid.
 */
public class Cell {
    private final Set<Integer> candidates;
    private int value;
    private CellCoordinate position;

    public Cell(int col, int row, int value) {
        this.candidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        this.value = value;
        this.position = new CellCoordinate(col, row);
    }

    /**
     * Constructs a new Cell given a 1d coordinate and a value
     * @param index
     * @param value
     */
    public Cell(int index, int value) {
        this.candidates = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        this.value = value;
        this.position = new CellCoordinate(index);
    }

    /**
     * Checks whether a cell has a value
     * @return true if cell has value other than 0
     */
    public boolean hasValue() {
        return this.value > 0;
    }

    /**
     * Gets the viable candidates for a cell
     * @return set of candidates
     */
    public Set<Integer> getCandidates() {
        return this.candidates;
    }

    public void removeCandidate(int candidate) {
        this.candidates.remove(candidate);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        candidates.remove(value);
        this.value = value;
    }

    public CellCoordinate getPosition() {
        return this.position;
    }
}
