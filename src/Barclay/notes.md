# backpack problem

Give *n* items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack

## dynamic programming: backpack problem
intput parameters are:
```
     * @param m : An integer m denotes the size of a backpack
     * @param A : Given n items with size A[i]
     * @param V : Given n items with value V[i]
     * @return : The maximum value
```
We need to create a table `dp[i][j]` of **n+1** rows, and **m+1** cols, each `dp[i][j]` represent **previous i items with size j**.
The value in `dp[i][j]` represent the maximum value we can get from previous i items with size of j. At the end, we only need to return `dp[n][m]`

code as below:
```
public int maxValue(int m, int[] A, int[] V) {

        int[][] dp = new int[A.length + 1][m + 1];

        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
                else if (A[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
                }
            }
        }
        return dp[A.length][m];
    }
```
- first condition
```
if (i == 0 || j == 0) {
    dp[i][j] = 0;
}
```
initial condition, previous 0 items correspond to any size will have zero value because no items, no values. Any items correspond to zero size will have zero value because no item will fit in zero size, and no item means no value.

- second condition
```
else if (A[i - 1] > j) {
     dp[i][j] = dp[i - 1][j];
}
```
if the size of ith item is larger than the corresponding size, with means ith item will never fit into the corresponding size, thus, the value here must be the same as previous i - 1 items correspond the size j.
- third condition
```
else {
    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
}
```
if the size of ith item is small or equal to the corresponding size j, we have to pick the max value of two condition
1. we won't pick the ith item, thus, the value must be previous i - 1 item correspond to size j
2. we pick the ith item with value V[i - 1] (because V[i - 1] correspond to the value of ith item), and sum up of the value of previous i - 1 item, with size j - A[i - 1] because, previous i - 1 items with have a maximum size of j - (A[i - 1);
