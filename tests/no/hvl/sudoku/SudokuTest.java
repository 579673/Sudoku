package no.hvl.sudoku;

import no.hvl.sudoku.model.ArraySudoku;
import no.hvl.sudoku.model.Cell;
import no.hvl.sudoku.model.interfaces.Sudoku;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
    Timing with JMH:
    https://openjdk.java.net/projects/code-tools/jmh/
    https://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
 */

public abstract class SudokuTest {
    Sudoku sudoku;
    int[] sudokuInput = {
            1, 0, 0, 2, 0, 0, 3, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            4, 0, 0, 5, 0, 0, 6, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            7, 0, 0, 8, 0, 0, 9, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    abstract Sudoku newSudoku(int[] input);
    abstract Sudoku cloneSudoku(Sudoku sudoku);

    @BeforeEach
    public void beforeEach() {
        this.sudoku = newSudoku(sudokuInput);
    }

    @Test
    void testGetRow() {
        assertEquals(
                List.of(1, 0, 0, 2, 0, 0, 3, 0, 0),
                sudoku.getRow(0).stream().map(Cell::getValue).collect(Collectors.toList())
        );
        assertEquals(
                List.of(0, 0, 0, 0, 0, 0, 0, 0, 0),
                sudoku.getRow(8).stream().map(Cell::getValue).collect(Collectors.toList())
        );
    }

    @Test
    void testGetCol() {
        assertEquals(
                List.of(1, 0, 0, 4, 0, 0, 7, 0, 0),
                sudoku.getColumn(0).stream().map(Cell::getValue).collect(Collectors.toList())
        );
    }

    @Test
    void testGetSquare() {
        assertEquals(
                List.of(1, 0, 0, 0, 0, 0, 0, 0, 0),
                sudoku.getSquare(0).stream().map(Cell::getValue).collect(Collectors.toList())
        );
        assertEquals(
                List.of(9, 0, 0, 0, 0, 0, 0, 0, 0),
                sudoku.getSquare(8).stream().map(Cell::getValue).collect(Collectors.toList())
        );
    }

    @Test
    void testGetCell() {
        assertEquals(new Cell(0, 1), sudoku.getCell(0));
    }

    @Test
    void testGetCellValue() {
        assertEquals(1, sudoku.getCellValue(0));
        assertEquals(1, sudoku.getCellValue(0, 0));
    }

    @Test
    void testSetCellValue() {
        sudoku.setCellValue(1, 9);
        assertEquals(9, sudoku.getCellValue(1));
        sudoku.setCellValue(2, 0, 8);
        assertEquals(8, sudoku.getCellValue(2));
    }

    @Test
    void testGetCellCandidates() {
        Set<Integer> control = Set.of(4, 5, 6, 7, 8, 9);
        assertEquals(
                control,
                sudoku.getCellCandidates(1)
        );
        assertEquals(
                control,
                sudoku.getCellCandidates(1, 0)
        );
    }

    @Test
    void testCloneReturnsDeepCopy() {
        Sudoku clone = cloneSudoku(sudoku);
        assertNotEquals(sudoku, clone);
        clone.setCellValue(1, 5);
        assertEquals(5, clone.getCellValue(1));
        assertNotEquals(sudoku.getCellValue(1), clone.getCellValue(1));
    }
}
