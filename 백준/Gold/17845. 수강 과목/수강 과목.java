import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int K = read();
        int[] dp = new int[N + 1];

        for (int i = 1; i <= K; i++) {
            int I = read();
            int T = read();

            for (int j = N; j > 0; j--) {
                if (T <= j) {
                    dp[j] = Math.max(dp[j], dp[j - T] + I);
                }
            }
        }

        sb.append(dp[N]);
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