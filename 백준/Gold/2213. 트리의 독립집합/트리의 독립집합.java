import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[][] dp;
    private static boolean[] visited;
    private static List<List<Integer>> graph;
    private static Queue<Integer> pq;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        dfs(1);
        sb.append(Math.max(dp[1][0], dp[1][1])).append("\n");

        boolean start = dp[1][0] > dp[1][1];
        visited = new boolean[visited.length];
        pq = new PriorityQueue<>();
        findDFS(1, start);

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
    }

    private static void findDFS(int cur, boolean select) {
        if (select) {
            pq.add(cur);
        }
        visited[cur] = true;

        for (Integer next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            if (select) {
                findDFS(next, false);
            } else {
                findDFS(next, dp[next][0] > dp[next][1]);
            }
        }

    }

    private static void dfs(int cur) {
        visited[cur] = true;

        for (Integer next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            dfs(next);

            dp[cur][0] += dp[next][1];
            dp[cur][1] += Math.max(dp[next][0], dp[next][1]);
        }
    }

    private static void input() throws IOException {
        int N = read();

        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = read();
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int from = read();
            int to = read();
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        visited = new boolean[N + 1];
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}