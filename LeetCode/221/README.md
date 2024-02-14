# [LeetCode-221](https://leetcode.com/problems/maximal-square/): Minimal Square Problem

### Legends:
1) Sizes will be written in row x col fashion.
2) Cells will be denoted as (row, col).

### Instinct:
1) Linear search the array (matrix) for max square size if square sizes are already available on every cell. This is O(n) operation where n=row*col.
2) Augment the array to store the adjacent following square sizes too.
3) Start reading the array from bottom right and increment the counter for current element to store sizes in the current cell. Like So:

| col | col | col | col | col |
| --- | --- | --- | --- | --- |
| A | A | A | B | B |
| A | A | B | B | B |    
| A | A | B | B | B |
| A | B | A | B | B |

to

| col | col | col | col | col |
| --- | --- | --- | --- | --- |
| A,3 | A,2 | A,1 | B,2 | B,1 |
| A,2 | A,1 | B,3 | B,2 | B,1 |    
| A,2 | A,1 | B,3 | B,2 | B,1 |
| A,1 | B,1 | A,1 | B,2 | B,1 |

3) All cells should be augmented to initialise their square sizes, i.e. square size for each cell will be 1x1. This can be denoted as 1 simply to reduce redundancy. Tuples can be denoted as (element, count, square) Like so:

| col | col | col | col | col |
| --- | --- | --- | --- | --- |
| A,3,1 | A,2,1 | A,1,1 | B,2,1 | B,1,1 |
| A,2,1 | A,1,1 | B,3,1 | B,2,1 | B,1,1 |    
| A,2,1 | A,1,1 | B,3,1 | B,2,1 | B,1,1 |
| A,1,1 | B,1,1 | A,1,1 | B,2,1 | B,1,1 |

5) Now start parsing the array from (max_row, 0) check next up cell (max_row-1, 0) to get the count of current element, cases here:
5.a) Count remains same: if first cell has count (A, 3), i.e.. count should be (A, greater than 1), if next up cell also has count (A, 3) that means we have a rectangle of A of 2x3. And max square size of 2. Put size 2 in next up cell as (A, 3, 2). Go up once again.
5.b) Count decreases: if first cell has count (A, 4), i.e.. count should be (A, greater than 1), if next up cell has count (A, 3) that means we have a square of 2x2. Put size 2 in next up cell as (A, 3, 2). Keep going up.
5.c) Count increases: if first cell has count (A, 3), i.e.. count should be (A, greater than 1), if next up cell has count (A, 4) that means we have a square of A of 3x3. And max square size of 3. Put size 3 in next up cell as (A, 4, 3). Go up once again.
6) The above stated rules will transform the matrix into:

| col | col | col | col | col |
| --- | --- | --- | --- | --- |
| A,3,2 | A,2,1 | A,1,1 | B,2,2 | B,1,1 |
| A,2,2 | A,1,1 | B,3,2 | B,2,1 | B,1,1 |    
| A,2,1 | A,1,1 | B,3,1 | B,2,2 | B,1,1 |
| A,1,1 | B,1,1 | A,1,1 | B,2,1 | B,1,1 |

