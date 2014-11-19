package cedms.logic;

public class floyd {
    
    public static int[][] shortestpath(int[][] adj, int[][] path) {
        int n = adj.length;
        int[][] ans = new int[n][n];
        copy(ans, adj);

       for (int k=0; k<n;k++) {
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (ans[i][k]+ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k]+ans[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }// Return the shortest path matrix.        
        return ans;
    }

    // Copies the contents of array b into array a. Assumes that both a and
    // b are 2D arrays of identical dimensions.
    public static void copy(int[][] a, int[][] b) {
        for (int i=0;i<a.length;i++)
            for (int j=0;j<a[0].length;j++)
                a[i][j] = b[i][j];
    }
// Returns the smaller of a and b.
    public static int min(int a, int b) {
        if (a < b)
            return a;
        else
            return b;
    }

}