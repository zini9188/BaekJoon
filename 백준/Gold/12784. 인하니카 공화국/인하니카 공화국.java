import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static List<List<Bridge>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            input();
            solution();
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Bridge(b, c));
            graph.get(b).add(new Bridge(a, c));
        }

        visited = new boolean[N + 1];
    }

    private static void solution() {
        int ans = dfs(1, 0);
        sb.append(ans).append("\n");
    }

    private static int dfs(int cur, int weight) {
        visited[cur] = true;

        int sum = 0;
        for (Bridge next : graph.get(cur)) {
            if (visited[next.v]) {
                continue;
            }
            sum += dfs(next.v, next.w);
        }

        if(cur == 1){
            return sum;
        }

        if (graph.get(cur).size() == 1) {
            return weight;
        }

        return Math.min(weight, sum);
    }

    private static class Bridge {

        int v, w;

        public Bridge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}