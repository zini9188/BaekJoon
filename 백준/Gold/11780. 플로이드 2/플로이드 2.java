import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static final int MAX = 100000 * 100 + 1;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n + 1][n + 1];
        Path[][] path = new Path[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = MAX;
                path[i][j] = new Path();
            }
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            graph[a][b] = Math.min(c, graph[a][b]);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    int in = graph[i][k] + graph[k][j];
                    if (graph[i][j] > in) {
                        graph[i][j] = in;
                        path[i][j].vertexes.clear();
                        for (int vertex : path[i][k].vertexes) {
                            path[i][j].vertexes.add(vertex);
                        }
                        path[i][j].vertexes.add(k);
                        for (int vertex : path[k][j].vertexes) {
                            path[i][j].vertexes.add(vertex);
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(graph[i][j] >= MAX){
                    graph[i][j] = 0;
                }
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] == 0) {
                    sb.append("0\n");
                    continue;
                }
                sb.append((path[i][j].vertexes.size() + 2)).append(" ");
                sb.append(i).append(" ");
                if (!path[i][j].vertexes.isEmpty()) {
                    for (int vertex : path[i][j].vertexes) {
                        sb.append(vertex).append(" ");
                    }
                }
                sb.append(j).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Path {
        ArrayList<Integer> vertexes = new ArrayList<>();
    }
}
