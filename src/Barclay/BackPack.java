package Barclay;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by linyujie on 10/30/17.
 */
public class BackPack {
    private int m;
    private int[] A;
    private int[] V;

    public void printM(){
        System.out.println(m);
    }
    public void printA(){
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
    public void printV(){
        for (int i = 0; i < V.length; i++) {
            System.out.print(V[i] + " ");
        }
        System.out.println();
    }

    //81 : (1,53.38,$45) (2,88.62,$98) (3,78.48,$3) (4,72.30,$76) (5,30.18,$9) (6,46.34,$48)
    public void parseString(String s) {

        int indexOfColon = s.indexOf(':');
        m = Integer.parseInt(s.substring(0, indexOfColon).trim());
        String rest = s.substring(indexOfColon + 1);
        String[] subs = rest.trim().replaceFirst("\\(", "").split("\\(");
        //System.out.print(subs.length);

        A = new int[subs.length];
        V = new int[subs.length];

        for (int i = 0; i < subs.length; i++) {
            //System.out.println("log" + subs[i]);
            int[] result = parseSubString(subs[i]);
            A[i] = result[1];
            V[i] = result[2];
        }

    }

    private int[] parseSubString(String sub) {
        int[] result = new int[3];
        sub = sub.replace(")", "").replace("$","").trim();
        String[] array = sub.split(",");
        result[0] = Integer.parseInt(array[0]);
        result[1] = Integer.parseInt(array[1]);
        result[2] = Integer.parseInt(array[2]);
        return result;
    }

    public int maxValueDp() {

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
    public int maxValueSort() {
        IndexDensity[] indexDensity = new IndexDensity[A.length];
        for(int i = 0; i < A.length; i++) {
            indexDensity[i] = new IndexDensity(i, (double)V[i] / A[i]);
        }
        Arrays.sort(indexDensity, new comp());
        int weight = 0;
        int value = 0;
        for (int i = 0; i < indexDensity.length; i++) {
            if (weight + A[indexDensity[i].index] <= m) {
                weight += A[indexDensity[i].index];
                value += V[indexDensity[i].index];
            }
        }
        return value;
    }

    private class IndexDensity {
        int index;
        double density;
        public IndexDensity(int index, double density) {
            this.index = index;
            this.density = density;
        }
    }

    private class comp implements Comparator<IndexDensity> {
        @Override
        public int compare(IndexDensity o1, IndexDensity o2) {
            return Double.compare(o2.density, o1.density);
        }
    }

    public static void main(String[] args) {
        String input = "81 : (1,35,$45) (2,88,$98) (3,78,$3) (4,72,$76) (5,30,$9) (6,46,$48)";
        BackPack bp = new BackPack();
        bp.parseString(input);
        System.out.println(bp.maxValueDp());
        System.out.println(bp.maxValueSort());

    }
}
