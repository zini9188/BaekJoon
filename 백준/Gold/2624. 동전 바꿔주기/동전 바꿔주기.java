import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] dp = new int[T + 1];
        List<Coin> coins = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int pi = Integer.parseInt(st.nextToken());
            int ni = Integer.parseInt(st.nextToken());
            coins.add(new Coin(pi, ni));
        }

        dp[0] = 1;
        for (Coin coin : coins) {
            for (int t = T; t > 0; t--) {
                for (int i = 1; i <= coin.cnt; i++) {
                    if (t - coin.cost * i >= 0) {
                        dp[t] += dp[t - coin.cost * i];
                    }
                }
            }
        }

        sb.append(dp[T]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Coin {

        int cost, cnt;

        public Coin(int cost, int cnt) {
            this.cost = cost;
            this.cnt = cnt;
        }
    }
}