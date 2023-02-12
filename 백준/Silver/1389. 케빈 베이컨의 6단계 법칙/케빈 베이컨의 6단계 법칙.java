import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] graph;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        graph = new int[N + 1][N + 1];
        initGraph();

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            graph[a][b] = 1;
            graph[b][a] = 1;
        }
        solution();
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void initGraph() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 5001;
                }
            }
        }
    }

    private static void solution() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += graph[i][j];
            }
            if (sum < res) {
                result = i;
                res = sum;
            }
        }
    }
}