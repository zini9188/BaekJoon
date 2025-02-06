import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int W;
    private static int T;
    private static int[][][] dp;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        T = read();
        W = read();

        dp = new int[2][W + 1][T + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < W + 1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        tree = new int[T];
        for (int i = 0; i < T; i++) {
            tree[i] = read() - 1;
        }

        int n = Math.max(dp(0, W, 0), dp(0, W - 1, 1));
        sb.append(n);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int dp(int time, int count, int pos) {
        if (count < 0) {
            return -1;
        }

        if (time == T) {
            return 0;
        }

        if (dp[pos][count][time] >= 0) {
            return dp[pos][count][time];
        }

        return dp[pos][count][time] = Math.max(dp(time + 1, count, pos),
                dp(time + 1, count - 1, 1 - pos)) + (tree[time] == pos ? 1 : 0);
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }
}