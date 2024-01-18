import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            long[][] graph = new long[p + 1][p + 1];
            for (int k = 0; k < p + 1; k++) {
                Arrays.fill(graph[k], INF);
                graph[k][k] = 0;
            }

            List<Integer> peoples = new ArrayList<>();
            for (int k = 0; k < n; k++) {
                peoples.add(Integer.parseInt(br.readLine()));
            }

            for (int k = 0; k < q; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if (graph[i][j] > d) {
                    graph[i][j] = graph[j][i] = d;
                }
            }

            for (int k = 1; k <= p; k++) {
                for (int i = 1; i <= p; i++) {
                    if (k == i || graph[k][i] == INF) {
                        continue;
                    }

                    for (int j = 1; j <= p; j++) {
                        graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
                    }
                }
            }

            long ans = Long.MAX_VALUE;
            int idx = 0;
            for (int i = 1; i <= p; i++) {
                long dist = 0;
                for (Integer people : peoples) {
                    dist += (int) Math.pow(graph[i][people], 2);
                }

                if (ans > dist) {
                    ans = dist;
                    idx = i;
                }
            }
            sb.append(idx).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}