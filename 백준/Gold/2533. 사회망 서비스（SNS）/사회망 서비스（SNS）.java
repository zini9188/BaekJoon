import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static List<List<Integer>> graph;
    private static int N;
    private static int[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        dfs(1);

        sb.append(Math.min(dp[1][0], dp[1][1]));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][0] = 1;
        dp[cur][1] = 0;

        for (Integer child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }

            dfs(child);

            dp[cur][0] += Math.min(dp[child][0], dp[child][1]);
            dp[cur][1] += dp[child][0];
        }
    }
}