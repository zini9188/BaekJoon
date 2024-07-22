import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static List<Boss> bosses;
    static StringTokenizer st;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<Long> damages = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            damages.add(Long.parseLong(br.readLine()));
        }
        damages.sort(Collections.reverseOrder());

        bosses = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            bosses.add(new Boss(Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken())));
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

    static class Boss {

        long P;
        int Q;

        public Boss(long p, int q) {
            P = p;
            Q = q;
        }
    }
}