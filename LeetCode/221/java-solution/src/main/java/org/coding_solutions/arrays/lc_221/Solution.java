package org.coding_solutions.arrays.lc_221;

public class Solution {
    char[][] sampleArray = {
            {'A', 'A', 'A', 'B', 'B'},
            {'A', 'A', 'B', 'B', 'B'},
            {'A', 'A', 'B', 'B', 'B'},
            {'A', 'B', 'A', 'B', 'B'},
    };

    private record CharAndCount(char c, int count){
        public CharAndCount {
            if(count<1) throw new IllegalArgumentException("Count must be greater than or equal to one");
        }
    }

    private record CharSquareCount(CharAndCount c, int sqCount) {}

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

    public static void main(String[] args) {
        Solution s = new Solution();
        CharAndCount[][] cs = s.augmentCountToArray(s.sampleArray);
        for (CharAndCount[] charAndCounts : cs) {
            for (CharAndCount charAndCount: charAndCounts)
                System.out.print(charAndCount+" ");
            System.out.println();
        }
    }
}
