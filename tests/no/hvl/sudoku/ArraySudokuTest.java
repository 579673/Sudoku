package no.hvl.sudoku;

import no.hvl.sudoku.model.ArraySudoku;
import no.hvl.sudoku.model.interfaces.Sudoku;

public class ArraySudokuTest extends SudokuTest {

    @Override
    Sudoku newSudoku(int[] input) {
        return new ArraySudoku(input);
    }

    @Override
    Sudoku cloneSudoku(Sudoku sudoku) {
        return new ArraySudoku((ArraySudoku)sudoku);
    }
}
