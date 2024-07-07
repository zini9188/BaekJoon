import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static long[][] dp;
    private static List<List<Integer>> graph;
    private static int[] weight;
    private static boolean[] visited;
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        dfs(1);
        long ans = 0;
        for (long w : dp[1]) {
            ans += w % MOD;
        }
        sb.append(ans % MOD);
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][weight[cur]]++;

        for (Integer next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            dfs(next);

            for (int i = 0; i < 10; i++) {
                dp[cur][i] += dp[next][i];
                if (weight[cur] <= i) {
                    dp[cur][weight[cur]] += dp[next][i] % MOD;
                }
            }
        }
    }

    private static void input() throws IOException {
        int N = read();

        visited = new boolean[N + 1];
        weight = new int[N + 1];
        dp = new long[N + 1][10];

        for (int i = 1; i <= N; i++) {
            weight[i] = read();
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int f = read();
            int t = read();
            graph.get(f).add(t);
            graph.get(t).add(f);
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}