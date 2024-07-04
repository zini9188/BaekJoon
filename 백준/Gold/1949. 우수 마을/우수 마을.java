import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static int[] amount;
    private static int[][] dp;
    private static boolean[] visited;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        amount = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            amount[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        dfs(1);

        sb.append(Math.max(dp[1][1], dp[1][0]));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][0] = amount[cur];
        dp[cur][1] = 0;

        for (Integer child : graph.get(cur)) {
            if (visited[child]) {
                continue;
            }

            dfs(child);

            dp[cur][0] += dp[child][1];
            dp[cur][1] += Math.max(dp[child][0], dp[child][1]);
        }
    }
}