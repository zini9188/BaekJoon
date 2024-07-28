import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        int N = read();
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int u = read();
            int v = read();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        dfs(1);

        sb.append(Math.min(dp[1][0], dp[1][1]));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int x) {
        visited[x] = true;
        dp[x][0] = 1;

        for (Integer next : graph.get(x)) {
            if (!visited[next]) {
                dfs(next);
                dp[x][0] += Math.min(dp[next][0], dp[next][1]);
                dp[x][1] += dp[next][0];
            }
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