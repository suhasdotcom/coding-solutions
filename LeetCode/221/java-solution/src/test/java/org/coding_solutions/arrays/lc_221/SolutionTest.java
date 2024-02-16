package org.coding_solutions.arrays.lc_221;

import org.coding_solutions.arrays.lc_221.model.JSONTestData;
import sks.utilities.test_prep.TestPrep;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SolutionTest {
    private final static Path testFileDir = Path.of("..", "test-data");

    @ParameterizedTest
    @MethodSource("jsonTestData")
    public void testAgainstFiles(JSONTestData jsonTestData) {
        assert jsonTestData.output() == 4;
    }

    public static List<JSONTestData> jsonTestData() throws IOException {
        return TestPrep.jsonTestData(testFileDir, JSONTestData.class);
    }
}