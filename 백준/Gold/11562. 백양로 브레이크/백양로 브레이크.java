import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = read();
        int m = read();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[][] connect = new boolean[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int u = read();
            int v = read();
            int b = read();

            graph.get(u).add(v);
            graph.get(v).add(u);
            connect[u][v] = true;
            if (b == 1) {
                connect[v][u] = true;
            }
        }

        int[][] change = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(change[i], Integer.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            Queue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            q.add(new int[]{i, 0});
            change[i][i] = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                if (change[i][cur[0]] > cur[1]) {
                    change[i][cur[0]] = cur[1];
                }

                if (visited[cur[0]]) {
                    continue;
                }
                visited[cur[0]] = true;

                for (Integer next : graph.get(cur[0])) {
                    if (!visited[next]) {
                        int nc = cur[1];
                        if (!connect[cur[0]][next]) {
                            nc++;
                        }

                        q.add(new int[]{next, nc});
                    }
                }
            }
        }

        int k = read();
        for (int i = 0; i < k; i++) {
            int s = read();
            int e = read();
            sb.append(change[s][e]).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

}