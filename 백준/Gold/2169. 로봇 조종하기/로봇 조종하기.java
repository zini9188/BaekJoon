import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] mars;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mars = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                mars[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] value = new int[N + 2][M + 2];
        int[][] left = new int[N + 2][M + 2];
        int[][] right = new int[N + 2][M + 2];

        for (int i = 0; i < N + 2; i++) {
            Arrays.fill(value[i], Integer.MIN_VALUE);
            Arrays.fill(left[i], Integer.MIN_VALUE);
            Arrays.fill(right[i], Integer.MIN_VALUE);
        }

        value[1][0] = 0;
        for (int i = 1; i <= M; i++) {
            value[1][i] = mars[1][i] + value[1][i - 1];
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                left[i][j] = Math.max(value[i - 1][j], left[i][j - 1]) + mars[i][j];
            }

            for (int j = M; j > 0; j--) {
                right[i][j] = Math.max(value[i - 1][j], right[i][j + 1]) + mars[i][j];
            }

            for (int j = 1; j <= M; j++) {
                value[i][j] = Math.max(left[i][j], right[i][j]);
            }
        }

        sb.append(value[N][M]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}