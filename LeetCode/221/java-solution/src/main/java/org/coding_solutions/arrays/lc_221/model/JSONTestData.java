package org.coding_solutions.arrays.lc_221.model;

record CharSquareCount(char c, int count, int sqCount) {}

public record JSONTestData(char[][] inputData, CharSquareCount[][] charCountData, int output) {
    public JSONTestData(char[][] inputData, int output) {
        this(inputData, null, output);
    }
}
