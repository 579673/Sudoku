package no.hvl.sudoku.model;

import no.hvl.sudoku.model.interfaces.Sudoku;

import java.util.List;

/**
 * An array implementation of Sudoku
 */
public class ArraySudoku implements Sudoku {
    private final int STANDARD_SIZE = 9 * 9;

    private Cell[] cells;

    public ArraySudoku() {
        this.cells = new Cell[STANDARD_SIZE];
    }


}
