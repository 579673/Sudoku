package no.hvl.sudoku.model;

import java.util.LinkedList;
import java.util.List;

/**
 * A Cell class for a sudoku game. Stores information about the cells in the Sudoku grid.
 */
public class Cell implements Cloneable {

    private final LinkedList<Integer> candidates;

    private int value;

    private CellCoordinate position;

    public Cell(int col, int row, int value) {
        this(col + row * 9, value);
    }

    /**
     * Constructs a new Cell given a 1d coordinate and a value
     * @param index
     * @param value
     */
    public Cell(int index, int value) {
        this.candidates = new LinkedList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
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
    public List<Integer> getCandidates() {
        return candidates;
    }

    public int getFirstCandidate() {
        if (candidates.isEmpty()) {
            return 0;
        }

        return candidates.getFirst();
    }

    public void removeCandidate(Integer candidate) {
        this.candidates.remove(candidate);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        candidates.clear();

        this.value = value;
    }

    public CellCoordinate getPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Cell)) {
            return false;
        }

        Cell other = (Cell) o;

        return this.value == other.getValue();
    }

    private Cell(LinkedList<Integer> candidates, int value, CellCoordinate position) {
        this.candidates = candidates;
        this.value = value;
        this.position = position;
    }

    public boolean hasSoleCandidate() {
        return candidates.size() == 1;
    }

    @Override
    public Object clone() {
        return new Cell(new LinkedList<>(candidates), value, (CellCoordinate) position.clone());
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
