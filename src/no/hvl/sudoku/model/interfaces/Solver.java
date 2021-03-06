package no.hvl.sudoku.model.interfaces;

/**
 * Defines a solver for a sudoku
 */
public interface Solver {
    void solve();
    Sudoku getSudoku();
    void setSudoku(Sudoku sudoku);
}
