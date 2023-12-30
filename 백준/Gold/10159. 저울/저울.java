import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            arr[a][b] = 1;
            arr[b][a] = -1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (arr[i][k] == arr[k][j] && arr[i][k] != 0) {
                        arr[i][j] = arr[i][k];
                    }
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            int cnt = 0;
            for (int j = 1; j < N + 1; j++) {
                if (i == j || arr[i][j] != 0 || arr[j][i] != 0) {
                    continue;
                }
                cnt++;
            }

            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}