package no.hvl.sudoku.model;

/**
 * Represents a coordinate for a Sudoku cell
 */
public class CellCoordinate {
    private int col;
    private int row;

    public CellCoordinate(int col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * Takes a 1d coordinate as a parameter and converts it to a 2d coordinate
     * @param index an array index
     */
    public CellCoordinate(int index) {
        this.col = index % 9;
        this.row = index / 9;
    }
}
