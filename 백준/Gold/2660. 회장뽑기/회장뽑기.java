import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 987654321;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(arr[i], INF);
        }

        String input;
        while (!(input = br.readLine()).equals("-1 -1")) {
            tokenizer = new StringTokenizer(input);
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            arr[a][b] = arr[b][a] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) {
                        continue;
                    }
                    arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
                }
            }
        }

        int[] dist = new int[N + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (arr[i][j] == INF) {
                    continue;
                }
                dist[i] = Math.max(arr[i][j], dist[i]);
            }
            ans = Math.min(dist[i], ans);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] == ans) {
                list.add(i);
            }
        }

        sb.append(ans).append(" ").append(list.size()).append("\n");
        for (Integer l : list) {
            sb.append(l).append(" ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}