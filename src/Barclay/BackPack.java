package Barclay;

/**
 * Created by linyujie on 10/30/17.
 */
public class BackPack {
    /**
     * @param m : An integer m denotes the size of a backpack
     * @param A : Given n items with size A[i]
     * @param V : Given n items with value V[i]
     * @return : The maximum value
     */
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

    public static void main(String[] args) {
        BackPack bp = new BackPack();
        int[] A = {2, 3, 5, 7};
        int[] V = {1, 5, 2, 4};
        int m = 10;
        System.out.print(bp.maxValue(m, A, V));
    }
}
