package no.hvl.sudoku.model;

import no.hvl.sudoku.model.interfaces.Solver;
import no.hvl.sudoku.model.interfaces.Sudoku;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SmartSudokuSolver implements Solver {

    private Sudoku sudoku;

    private Deque<Sudoku> stack;

    public SmartSudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.stack = new ArrayDeque<>(4);
    }

    public SmartSudokuSolver() {
        this.sudoku = null;
        this.stack = null;
    }

    @Override
    public void solve() {
        System.out.println(sudoku + "\n");

        while (noEndConditionReached()) {
            solveIteration();
        }
    }

    public void solveIteration() {
        boolean moveMade = resolveCellsWithSoleCandidate() || resolveCellsWithUniqueCandidate();

        if (!moveMade) {
            guessValueAndStoreSnapshotOnStack();
        }
    }

    private void guessValueAndStoreSnapshotOnStack() {
        Optional<Cell> maybeCellWithFewestCandidates = findCellWithFewestCandidates();

        if (maybeCellWithFewestCandidates.isEmpty()) {
            return;
        }

        Cell cellWithFewestCandidates = maybeCellWithFewestCandidates.get();

        int candidate = cellWithFewestCandidates.getFirstCandidate();
        storeSudokuOnStack();
        int cellNumber = cellWithFewestCandidates.getPosition().getNumber();
        stack.peek().getCell(cellNumber).removeCandidate(candidate);
        sudoku.setCellValue(cellNumber, candidate);
    }

    public boolean noEndConditionReached() {
        if (sudoku.isSolved()) {
            return false;
        }

        if (!sudoku.isSolvable() && stack.isEmpty()) {
            return false;
        }

        if (!sudoku.isSolvable()) {
            sudoku = stack.pop();
        }

        return true;
    }

    private void storeSudokuOnStack() {
        stack.push(new ArraySudoku((ArraySudoku)sudoku));
    }

    private Optional<Cell> findCellWithFewestCandidates() {
        return sudoku.getCells().stream()
            .filter(c -> !c.hasValue())
            .min(Comparator.comparingInt(c -> c.getCandidates().size()));
    }

    private boolean resolveCellsWithUniqueCandidate() {
        boolean changeMade = false;

        for (Cell cell : sudoku.getCells()) {
            int rowNumber = cell.getPosition().getRow();
            int colNumber = cell.getPosition().getCol();
            int squareNumber = cell.getPosition().getSquareNumber();

            List<Integer> rowCandidates = sudoku.getRow(rowNumber).stream()
                .filter(c -> c != cell)
                .flatMap(c -> c.getCandidates().stream())
                .collect(Collectors.toList());
            List<Integer> colCandidates = sudoku.getColumn(colNumber).stream()
                .filter(c -> c != cell)
                .flatMap(c -> c.getCandidates().stream())
                .collect(Collectors.toList());
            List<Integer> squareCandidates = sudoku.getSquare(squareNumber).stream()
                .filter(c -> c != cell)
                .flatMap(c -> c.getCandidates().stream())
                .collect(Collectors.toList());

            int uniqueCandidate = 0;

            for (int candidate : cell.getCandidates()) {
                if (!rowCandidates.contains(candidate) || !colCandidates.contains(candidate) ||
                        !squareCandidates.contains(candidate)) {
                    uniqueCandidate = candidate;
                    break;
                }
            }

            if (uniqueCandidate != 0) {
                changeMade = true;
                sudoku.setCellValue(cell.getPosition().getNumber(), uniqueCandidate);
            }
        }

        return changeMade;
    }

    private boolean resolveCellsWithSoleCandidate() {
        Iterator<Cell> cellsWithSoleCandidate = sudoku.getCells().stream()
            .filter(Cell::hasSoleCandidate)
            .iterator();

        boolean changeMade = cellsWithSoleCandidate.hasNext();

        cellsWithSoleCandidate.forEachRemaining(c -> {
            sudoku.setCellValue(c.getPosition().getNumber(), c.getFirstCandidate());
        });

        return changeMade;
    }

    @Override
    public Sudoku getSudoku() {
        return sudoku;
    }

    @Override
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.stack = new ArrayDeque<>();
    }

}
