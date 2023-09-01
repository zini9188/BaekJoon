import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        long[][] graph = new long[N][N];
        graph[0][0] = 1;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(tokenizer.nextToken());
                if (num == 0) {
                    continue;
                }
                if (i + num < N) {
                    graph[i + num][j] += graph[i][j];
                }
                if (j + num < N) {
                    graph[i][j + num] += graph[i][j];
                }
            }
        }

        sb.append(graph[N - 1][N - 1]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}