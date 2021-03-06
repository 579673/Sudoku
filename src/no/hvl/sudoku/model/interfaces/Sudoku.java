package no.hvl.sudoku.model.interfaces;

import no.hvl.sudoku.model.Cell;

import java.util.List;
import java.util.Set;

/**
 * Defines a sudoku board
 */
public interface Sudoku {
    List<Cell> getRow(int rowNumber);
    List<Cell> getColumn(int colNumber);
    List<Cell> getSquare(int squareNumber);
    List<Cell> getAllRelatedCells(int cellNumber);
    List<Cell> getAllRelatedCells(int colNumber, int rowNumber);
    Cell getCell(int cellNumber);
    Cell getCell(int colNumber, int rowNumber);
    int getCellValue(int cellNumber);
    int getCellValue(int colNumber, int rowNumber);
    void setCellValue(int cellNumber, int value);
    void setCellValue(int colNumber, int rowNumber, int value);
    List<Integer> getCellCandidates(int cellNumber);
    List<Integer> getCellCandidates(int colNumber, int rowNumber);
    List<Cell> getCells();
    boolean isSolvable();
    boolean isSolved();
}
