import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[][] dp;
    private static List<List<Integer>> graph;
    private static boolean[] visit;
    private static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];
        graph = new ArrayList<>();
        graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<>());
            dp[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= N; i++) {
            int boss = Integer.parseInt(st.nextToken());
            graph.get(boss).add(i);
        }

        visit = new boolean[N + 1];
        pq = new PriorityQueue<>();
    }

    private static void solution() {
        dfs(1);

        sb.append(dp[1][0]).append(" ").append(dp[1][1]).append("\n");
        findDFS(1, true);
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
        sb.append("-1\n");

        findDFS(1, false);
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
        sb.append("-1\n");
    }

    private static void findDFS(int cur, boolean isVisit) {
        if (isVisit) {
            pq.add(cur);
        }

        for (Integer next : graph.get(cur)) {
            if (isVisit) {
                findDFS(next, false);
            } else {
                findDFS(next, visit[next]);
            }
        }
    }

    private static void dfs(int cur) {
        visit[cur] = true;

        for (Integer next : graph.get(cur)) {
            dfs(next);
            dp[cur][0] += dp[next][1];
            if (dp[next][0] >= dp[next][1]) {
                dp[cur][1] += dp[next][0];
                visit[next] = true;
            } else {
                dp[cur][1] += dp[next][1];
                visit[next] = false;
            }
        }
    }
}