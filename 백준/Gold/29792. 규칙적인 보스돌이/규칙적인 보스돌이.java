import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static List<Boss> bosses;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();

        ArrayList<Long> damages = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            damages.add(readLong());
        }
        damages.sort(Collections.reverseOrder());

        bosses = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            bosses.add(new Boss(readLong(), readInt()));
        }

        long[] dp;
        long sum = 0;
        for (int i = 0; i < M; i++) {
            dp = new long[901];
            for (Boss boss : bosses) {
                long eliminate = (boss.P / damages.get(i));
                if (boss.P % damages.get(i) > 0) {
                    eliminate++;
                }

                for (int t = 900; eliminate <= 900 && t >= eliminate; t--) {
                    dp[t] = Math.max(dp[t], dp[(int) (t - eliminate)] + boss.Q);
                }
            }
            sum += dp[900];
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static long readLong() throws IOException {
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
    
    static class Boss {

        long P;
        int Q;

        public Boss(long p, int q) {
            P = p;
            Q = q;
        }
    }
}