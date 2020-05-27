package no.hvl.sudoku;

import no.hvl.sudoku.model.ArraySudoku;
import no.hvl.sudoku.model.SmartSudokuSolver;
import no.hvl.sudoku.model.interfaces.Solver;
import no.hvl.sudoku.model.interfaces.Sudoku;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SudokuSolverTest {
    Sudoku[] sudokus;

    int[] simpleSudokuArray = {
            9, 0, 6, 0, 7, 0, 0, 0, 0,
            0, 0, 0, 2, 0, 0, 0, 9, 0,
            8, 5, 1, 0, 0, 9, 7, 0, 0,
            5, 6, 0, 0, 2, 0, 9, 0, 3,
            0, 0, 0, 0, 1, 0, 6, 8, 0,
            0, 0, 7, 6, 0, 0, 2, 0, 4,
            0, 1, 9, 0, 0, 4, 0, 3, 8,
            7, 0, 4, 5, 0, 8, 1, 6, 0,
            0, 8, 5, 0, 3, 0, 4, 7, 0
    };
    int[] expertSudokuArray = {
            0, 0, 0, 1, 0, 0, 6, 0, 5,
            0, 0, 0, 0, 0, 6, 0, 0, 0,
            0, 0, 2, 5, 0, 9, 0, 3, 0,
            9, 0, 0, 0, 7, 0, 0, 6, 0,
            2, 0, 0, 0, 3, 0, 1, 0, 0,
            0, 0, 0, 0, 0, 0, 3, 0, 2,
            7, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 4, 0, 0, 0, 0, 0, 2, 0,
            0, 0, 1, 0, 6, 5, 4, 0, 0,
    };

    @BeforeAll
    void setUp() {
        sudokus = new Sudoku[2];
        sudokus[0] = new ArraySudoku(simpleSudokuArray);
        sudokus[1] = new ArraySudoku(expertSudokuArray);
    }



}
