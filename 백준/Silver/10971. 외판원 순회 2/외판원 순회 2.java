import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer tokenizer;
    static int[][] cost;
    static int N;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
            visited[i] = false;
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int start, int from, int depth, int totalCost) {
        if (depth == N - 1 && cost[from][start] != 0) {
            answer = Math.min(answer, totalCost + cost[from][start]);
            return;
        }

        for (int to = 0; to < N; to++) {
            if (visited[to] || cost[from][to] == 0) {
                continue;
            }

            visited[to] = true;
            dfs(start, to, depth + 1, totalCost + cost[from][to]);
            visited[to] = false;
        }
    }
}