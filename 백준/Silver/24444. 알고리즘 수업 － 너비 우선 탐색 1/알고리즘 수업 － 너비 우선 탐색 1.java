import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();
        int R = read();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = read();
            int v = read();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[N + 1];
        int[] visit = new int[N + 1];
        visited[R] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(R);
        int cnt = 0;
        while (!q.isEmpty()) {
            int n = q.poll();
            visit[n] = ++cnt;
            Collections.sort(graph.get(n));
            for (int next : graph.get(n)) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(visit[i]).append("\n");
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