import java.io.*;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int K = read();

        final int INF = 100001;
        int[] dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int caffeine = read();

            for (int k = K; k >= 0; k--) {
                if (k - caffeine >= 0) {
                    dp[k] = Math.min(dp[k], dp[k - caffeine] + 1);
                }
            }
        }

        sb.append(dp[K] == INF ? "-1" : dp[K]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}