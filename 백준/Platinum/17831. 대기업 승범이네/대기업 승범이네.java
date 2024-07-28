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
    private static int[] weight;

    public static void main(String[] args) throws IOException {
        int N = read();
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];
        weight = new int[N + 1];
        dp = new int[N + 1][2];

        for (int child = 2; child <= N; child++) {
            int parent = read();
            graph.get(parent).add(child);
        }

        for (int i = 1; i <= N; i++) {
            weight[i] = read();
        }

        dfs(1);

        sb.append(Math.max(dp[1][0], dp[1][1]));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int x) {
        visited[x] = true;
        int sum = 0, max = 0;
        for (int next : graph.get(x)) {
            if (!visited[next]) {
                dfs(next);
                dp[x][1] += Math.max(dp[next][0], dp[next][1]);
                sum += Math.max(dp[next][0], dp[next][1]);
                int temp = dp[next][1] + weight[next] * weight[x]
                        - Math.max(dp[next][0], dp[next][1]);
                if (temp > max) {
                    max = temp;
                }
            }
        }
        dp[x][0] = sum + max;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}