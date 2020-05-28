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



    @TestFactory
    Stream<DynamicTest> dynamicTestForArraySolver() {
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
