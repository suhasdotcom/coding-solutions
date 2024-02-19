package org.coding_solutions.arrays.lc_221;

public class Solution {
    char[][] sampleArray = {
            {'A', 'A', 'A', 'B', 'B'},
            {'A', 'A', 'B', 'B', 'B'},
            {'A', 'A', 'B', 'B', 'B'},
            {'A', 'B', 'A', 'B', 'B'},
    };
    
    char[][] sampleArray2 = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
    
    char[][] smallArr = {{'0','1'},{'1','0'}};

    char[][] bigArray = {
            {'A', 'A', 'A', 'B', 'B', 'B'},
            {'A', 'A', 'B', 'B', 'B', 'B'},
            {'A', 'A', 'B', 'B', 'B', 'B'},
            {'A', 'B', 'B', 'B', 'B', 'B'},
            {'A', 'B', 'B', 'B', 'B', 'B'},
    };

    private record CharAndCount(char c, int count){
        public CharAndCount {
            if(count<1) throw new IllegalArgumentException("Count must be greater than or equal to one");
        }
    }

    private record CharSquareCount(char c, int count, int sqCount) {}

    /**
     *
     * @param theArray assumes this array to have same number of columns in all rows.
     * @return {@link CharAndCount} array
     */
    CharAndCount[][] augmentCountToArray(char[][] theArray) {
        CharAndCount[][] charCountArray = new CharAndCount[theArray.length][theArray[0].length];
        for(int i=theArray.length-1; i>=0; i--) {
            int count = 0;
            char lastElement = theArray[i][theArray[i].length-1];
            for(int j=theArray[i].length-1; j>=0; j--) {
                if(theArray[i][j]==lastElement) {
                    count++;
                    charCountArray[i][j] = new CharAndCount(lastElement, count);
                }
                else {
                    count = 0;
                    lastElement = theArray[i][j];
                    j++;
                }
            }
        }
        return charCountArray;
    }

    CharSquareCount[][] augmentSquareToArray(char[][] theArray) {
        final CharAndCount[][] cc  = augmentCountToArray(theArray);
        return augmentSquareToArray(cc);
    }

    CharSquareCount[][] augmentSquareToArray(CharAndCount[][] theArray) {
        CharSquareCount[][] csc = new CharSquareCount[theArray.length][theArray[0].length];
        for(int j=0; j<theArray[0].length; j++) {
            int sqCount = 1;
            CharAndCount lastElement = theArray[theArray.length-1][j];
            int minSq = lastElement.count;
            for(int i=theArray.length-1; i>=0; i--) {
                CharAndCount currElement = theArray[i][j];
                if(i==theArray.length-1)
                    csc[i][j]=new CharSquareCount(lastElement.c, lastElement.count, sqCount);
                else{
                    if(currElement.c!=lastElement.c) {
                        sqCount = 1;
                        csc[i][j] = new CharSquareCount(currElement.c, currElement.count, sqCount);
                    }
                    else {
                        if(currElement.count <= lastElement.count) {
                            if(sqCount<currElement.count) sqCount++;
                            csc[i][j] = new CharSquareCount(currElement.c, currElement.count, Math.min(sqCount, currElement.count));
                        } else {
                            if(sqCount>currElement.count && sqCount<minSq) sqCount++;
                            csc[i][j] = new CharSquareCount(currElement.c, currElement.count, sqCount);
                        }
                    }
                }
                lastElement = currElement;
            }
        }
        return csc;
    }

    static void printMatrix(final char[][] theMatrix) {
        for (char[] row : theMatrix) {
            for (char col: row)
                System.out.print(col+" ");
            System.out.println();
        }
    }

    static <T> void printMatrix(final T[][] theMatrix) {
        for (T[] row : theMatrix) {
            for (T col: row)
                System.out.print(col+" ");
            System.out.println();
        }
    }

    public int maxSquare(final char[][] theMatrix) {
        CharAndCount[][] cs = augmentCountToArray(theMatrix);
        CharSquareCount[][] csc = augmentSquareToArray(cs);
        int max = 1;
        for(CharSquareCount[] cr: csc) {
            for(CharSquareCount cc: cr)
                if(cc.sqCount>max) max = cc.sqCount;
        }
        return max;
    }

    public int maxSquareForElement(final char[][] theMatrix, char element) {
        CharAndCount[][] cs = augmentCountToArray(theMatrix);
        CharSquareCount[][] csc = augmentSquareToArray(cs);
        int max = 0;
        for(CharSquareCount[] cr: csc) {
            for(CharSquareCount cc: cr)
                if(cc.c==element && cc.sqCount>max) max = cc.sqCount;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char [][] arr = s.bigArray;
        printMatrix(arr);
        CharAndCount[][] cs = s.augmentCountToArray(arr);
        printMatrix(cs);
        CharSquareCount[][] csc = s.augmentSquareToArray(cs);
        printMatrix(csc);
    }
}
