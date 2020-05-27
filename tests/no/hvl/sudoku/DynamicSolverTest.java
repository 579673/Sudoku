package no.hvl.sudoku;

import no.hvl.sudoku.model.ArraySudoku;
import no.hvl.sudoku.model.SmartSudokuSolver;
import no.hvl.sudoku.model.interfaces.Solver;
import no.hvl.sudoku.model.interfaces.Sudoku;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DynamicSolverTest {
    List<Sudoku> sudokus;

    @BeforeAll
    void setUp() {
        try {
            URL sudokuUrl = new URL("https://raw.githubusercontent.com/maxbergmark/sudoku-solver/master/all_17_clue_sudokus.txt");
            HttpsURLConnection conn = (HttpsURLConnection)sudokuUrl.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sudokus = br.lines()
                    .filter(line -> line.length() == 81)
                    .map(line -> line.chars().map(c -> c - '0').toArray())
                    .map(ArraySudoku::new)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            sudokus = new ArrayList<>();
            e.printStackTrace();
        }
    }

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
            0, 0, 1, 0, 6, 5, 4, 0, 0
    };
    int[] emptySudokuArray = {
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0
    };

    int[] seventeenClueSudokuArray = {
            0, 0, 0, 7, 0, 0, 0, 0, 0,
            1, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 4, 3, 0, 2, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 6,
            0, 0, 0, 5, 0, 9, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 4, 1, 8,
            0, 0, 0, 0, 8, 1, 0, 0, 0,
            0, 0, 2, 0, 0, 0, 0, 5, 0,
            0, 4, 0, 0, 0, 0, 3, 0, 0
    };

    @TestFactory
    Stream<DynamicTest> dynamicTestForArraySolver() {
        List<Sudoku> inputList = List.of(
                new ArraySudoku(simpleSudokuArray),
                new ArraySudoku(expertSudokuArray),
                new ArraySudoku(emptySudokuArray),
                new ArraySudoku(seventeenClueSudokuArray)
        );

        return sudokus.stream()
                .map(s -> DynamicTest.dynamicTest(
                        "Sudoku",
                        () -> {
                            Solver solver = new SmartSudokuSolver(s);
                            solver.solve();
                            assertTrue(solver.getSudoku().isSolved());
                        }
                ));
    }
}
