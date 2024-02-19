package org.coding_solutions.arrays.lc_221;

import org.coding_solutions.arrays.lc_221.model.JSONTestData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import sks.utilities.test_prep.TestPrep;

import java.io.IOException;
import java.nio.file.Path;

public class SolutionTest {
    private final static Path testFileDir = Path.of("..", "test-data");
    private final Solution sut = new Solution();

    /**
     * Always statically initialise {@link TestPrep#testDirectory} or any other {@link TestPrep} resource because its
     * methods need to be called statically in {@link MethodSource}. The same can be done in a static block.
     */
    @BeforeAll
    public static void init() {
        TestPrep.testDirectory = testFileDir;
    }

    @ParameterizedTest
    @MethodSource("sks.utilities.test_prep.TestPrep#jsonFilesInDir")
    public void testJSONAgainstFiles(String jsonFilePath) throws IOException {
        JSONTestData obj = TestPrep.jsonFileToData(jsonFilePath, JSONTestData.class);
        var cc = sut.augmentSquareToArray(obj.inputData());
        TestPrep.printMatrix(cc);
        int maxSquare = sut.maxSquare(obj.inputData());
        assert obj.output() == maxSquare*maxSquare;
    }
}